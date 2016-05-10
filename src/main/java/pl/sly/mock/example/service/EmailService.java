package pl.sly.mock.example.service;

import pl.sly.mock.example.model.dto.EmailMessage;

public interface EmailService {
    EmailMessage sendEmail(String email, String content);
}
