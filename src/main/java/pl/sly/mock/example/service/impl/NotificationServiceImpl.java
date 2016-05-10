package pl.sly.mock.example.service.impl;

import com.google.common.base.Optional;
import org.springframework.stereotype.Service;
import pl.sly.mock.example.model.dto.Callback;
import pl.sly.mock.example.service.*;

import javax.annotation.Resource;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Resource
    private SMSService smsService;

    @Resource
    private EmailService emailService;

    @Resource
    private CallbackService callbackService;

    @Resource
    private PushService pushService;

    @Override
    public void sendNotification(Optional email, Optional phoneNumber, String notificationContent) {
        if (email.isPresent()) {
            emailService.sendEmail(email.get().toString(), notificationContent);
        }

        if (phoneNumber.isPresent()) {
            smsService.sendSMS(phoneNumber.get().toString(), notificationContent);
        }
    }

    @Override
    public void doCallback() {
        callbackService.invokeCallback(new Callback() {
            @Override
            public void onSuccess() {
                pushService.pushStatus("success");
            }

            @Override
            public void onFail() {
                pushService.pushStatus("fail");
            }
        });
    }
}