package pl.sly.mock.example.service;

import pl.sly.mock.example.model.dto.SMSMessage;

public interface SMSService {
    SMSMessage sendSMS(String phoneNumber, String content);
}
