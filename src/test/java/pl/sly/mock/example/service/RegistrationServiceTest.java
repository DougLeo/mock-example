package pl.sly.mock.example.service;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.sly.mock.example.model.dto.User;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class RegistrationServiceTest {
    @Resource
    RegistrationService registrationService;

    @Mock
    EmailService emailServiceMock;

    @Captor
    ArgumentCaptor<String> emailCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(registrationService, "emailService", emailServiceMock);
    }

    @Test
    public void testRegisterUserVerifyMailService() {
        User user = new User("Jim", "jim@example.com");

        registrationService.registerUser(user);

        Mockito.verify(emailServiceMock).sendEmail(emailCaptor.capture(), Mockito.anyString());
        Assert.assertNotNull(emailCaptor);
        Assert.assertEquals("jim@example.com", emailCaptor.getValue());
    }
}
