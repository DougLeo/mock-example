package pl.sly.mock.example.service.impl;

import org.springframework.stereotype.Service;
import pl.sly.mock.example.model.dto.SMSMessage;
import pl.sly.mock.example.service.SMSService;

@Service
public class SMSServiceImpl implements SMSService {
    @Override
    public SMSMessage sendSMS(String phoneNumber, String content) {
        return null;
    }
}
