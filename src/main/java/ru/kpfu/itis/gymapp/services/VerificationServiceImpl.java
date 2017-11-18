package ru.kpfu.itis.gymapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.models.VerificationToken;
import ru.kpfu.itis.gymapp.models.enums.State;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.repositories.VerificationTokenRepository;

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
public class VerificationServiceImpl implements VerificationService {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void makeVerification(User user, String url) {
        String token = UUID.randomUUID().toString();
        verificationTokenRepository.save(new VerificationToken(token, user));
        String link = url + "/signup/accept?token=" + token;
        String text =
                String.format("Thanks for signing up ! You must follow this link to activate your account: rn %s", link);

        executorService.submit(() -> emailService.sendMail(text, "Confirm your account", user.getLogin()))
        ;

    }

    @Override
    public User confirmVerification(String token) {
        VerificationToken verificationToken =
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
