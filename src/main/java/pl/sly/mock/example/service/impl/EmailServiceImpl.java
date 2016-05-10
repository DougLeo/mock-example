package pl.sly.mock.example.service.impl;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import pl.sly.mock.example.model.dto.EmailMessage;
import pl.sly.mock.example.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public EmailMessage sendEmail(String email, String content) {
        boolean valid = EmailValidator.getInstance().isValid(email);

        if (!valid) {
            throw new IllegalArgumentException("Invalid email!");
        }

        //send email

        return null;
    }
}
