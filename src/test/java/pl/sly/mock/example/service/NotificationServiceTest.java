package pl.sly.mock.example.service;

import com.google.common.base.Optional;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.sly.mock.example.model.dto.Callback;
import pl.sly.mock.example.model.dto.EmailMessage;
import pl.sly.mock.example.model.dto.SMSMessage;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class NotificationServiceTest {
    @Resource
    NotificationService notificationService;

    @Mock
    SMSService smsServiceMock;

    @Mock
    EmailService emailServiceMock;

    @Mock
    CallbackService callbackServiceMock;

    @Mock
    PushService pushServiceMock;

    @Captor
    ArgumentCaptor<String> pushServiceCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(notificationService, "smsService", smsServiceMock);
        ReflectionTestUtils.setField(notificationService, "emailService", emailServiceMock);
        ReflectionTestUtils.setField(notificationService, "callbackService", callbackServiceMock);
        ReflectionTestUtils.setField(notificationService, "pushService", pushServiceMock);
    }

    @Test
    public void testSendEmail() {
        //given
        EmailMessage emailMessage = new EmailMessage();
        Mockito.when(emailServiceMock.sendEmail(Matchers.anyString(), Matchers.anyString())).thenReturn(emailMessage);

        //when
        notificationService.sendNotification(Optional.of("some mail"), Optional.absent(), "content");

        //then
        Mockito.verify(emailServiceMock, Mockito.times(1)).sendEmail(Matchers.anyString(), Matchers.anyString());
        Mockito.verify(smsServiceMock, Mockito.never()).sendSMS(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void testSendSms() {
        //given
        SMSMessage smsMessage = new SMSMessage();
        Mockito.when(smsServiceMock.sendSMS(Matchers.anyString(), Matchers.anyString())).thenReturn(smsMessage);

        //when
        notificationService.sendNotification(Optional.absent(), Optional.of("some number"), "content");

        //then
        Mockito.verify(smsServiceMock, Mockito.times(1)).sendSMS(Matchers.anyString(), Matchers.anyString());
        Mockito.verify(emailServiceMock , Mockito.never()).sendEmail(Matchers.anyString(), Matchers.anyString());
    }

    @Test
    public void testCallbackSuccess() {
        //given
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                Callback callback = (Callback) invocationOnMock.getArguments()[0];
                callback.onSuccess();
                return null;
            }
        }).when(callbackServiceMock).invokeCallback(Mockito.any(Callback.class));

        //when
        notificationService.doCallback();

        //then
        Mockito.verify(pushServiceMock).pushStatus(pushServiceCaptor.capture());
        Assert.assertNotNull(pushServiceCaptor);
        Assert.assertEquals("success", pushServiceCaptor.getValue());
    }
}