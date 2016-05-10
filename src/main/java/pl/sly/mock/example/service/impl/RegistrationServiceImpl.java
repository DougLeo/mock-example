package pl.sly.mock.example.service.impl;

import org.springframework.stereotype.Service;
import pl.sly.mock.example.model.dto.User;
import pl.sly.mock.example.service.EmailService;
import pl.sly.mock.example.service.RegistrationService;

import javax.annotation.Resource;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Resource
    EmailService emailService;

    @Override
    public void registerUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        emailService.sendEmail(user.getEmail(), "content");
    }
}
