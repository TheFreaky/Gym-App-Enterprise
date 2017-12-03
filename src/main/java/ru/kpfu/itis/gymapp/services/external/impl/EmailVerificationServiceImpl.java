package ru.kpfu.itis.gymapp.services.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.models.enums.State;
import ru.kpfu.itis.gymapp.models.token.EmailVerificationToken;
import ru.kpfu.itis.gymapp.repositories.EmailVerificationTokenRepository;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.services.EmailService;
import ru.kpfu.itis.gymapp.services.external.EmailVerificationService;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 18.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {
    @Autowired
    private EmailVerificationTokenRepository verificationTokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void makeVerification(User user, String to) {
        String token = UUID.randomUUID().toString();
        verificationTokenRepository.save(new EmailVerificationToken(token, user));
        String link = "http://localhost:8080/signup/accept?token=" + token;
        String text =
                String.format("Thanks for signing up ! You must follow this link to activate your account: \n <a href=\"%s\">%s</a>", link, link);

        executorService.submit(() -> emailService.sendMail(text, "Confirm your account", to));
    }

    @Override
    public User confirmVerification(String token) {
        EmailVerificationToken verificationToken =
                verificationTokenRepository.getByTokenAndExpiryDateAfter(token, new Date());
        User user = null;

        if (verificationToken != null && verificationToken.getUser().getState().equals(State.NOT_CONFIRMED)) {
            user = verificationToken.getUser();
            user.setState(State.CONFIRMED);
            userRepository.save(user);
        }
        return user;
    }
}
