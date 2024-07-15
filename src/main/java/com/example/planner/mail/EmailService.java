package com.example.planner.mail;
import com.example.planner.exceptions.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private static final String EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";



    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void validateEmail(String email) {
        if (!patternMatches(email)) {
            throw new InvalidEmailException(email);
        }
    }

    private static boolean patternMatches(String emailAddress) {
        return Pattern.compile(EmailService.EMAIL_REGEX_PATTERN)
                .matcher(emailAddress)
                .matches();
    }
}
