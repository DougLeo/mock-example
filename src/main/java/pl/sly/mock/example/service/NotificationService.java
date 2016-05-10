package pl.sly.mock.example.service;

import com.google.common.base.Optional;

public interface NotificationService {
    void sendNotification(Optional email, Optional phoneNumber, String notificationContent);
    void doCallback();
}