package ru.kpfu.itis.gymapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 18.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String text, String subject, String to) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setContent(text, "text/html");
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
