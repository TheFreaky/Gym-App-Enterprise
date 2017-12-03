package ru.kpfu.itis.gymapp.services.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.models.token.SmsVerificationToken;
import ru.kpfu.itis.gymapp.repositories.SmsVerificationTokenRepository;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.services.external.SmsVerificationService;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 25.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class SmsVerificationServiceImpl implements SmsVerificationService {

    @Value("${sms.aero.user}")
    private String smsAeroLogin;

    @Value("${sms.aero.password}")
    private String smsAeroPassword;

    @Value("${sms.aero.from}")
    private String smsAeroFrom;

    @Value("${sms.aero.type}")
    private String smsAeroType;

    @Value("${sms.aero.url}")
    private String smsAeroUrl;

    @Autowired
    private SmsVerificationTokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void makeVerification(User user, String number) {
        Integer token = new Random().nextInt(9000) + 1000;
        tokenRepository.save(new SmsVerificationToken(token.toString(), user, number));

        String formattedNumber = number.replace("+", "");

        Future<Boolean> result = executorService.submit(() -> {
            String text = "Pimp Yorself. Код подтверждения: " + token;
            String request = smsAeroUrl + "?user="
                    + smsAeroLogin + "&password="
                    + smsAeroPassword + "&to="
                    + formattedNumber
                    + "&text=" + text
                    + "&from="
                    + smsAeroFrom + "&type="
                    + smsAeroType;
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(request, String.class);
            if (responseEntity.getBody().contains("accepted")) {
                return true;
            } else {
                throw new IllegalArgumentException("Проблемы с номером телефона");
            }
        });
    }

    @Override
    public User confirmVerification(String token) {
        SmsVerificationToken verificationToken = tokenRepository.getByTokenAndExpiryDateAfter(token, new Date());
        User user = null;

        if (verificationToken != null) {
            user = verificationToken.getUser();
            user.setPhone(verificationToken.getPhone());
            userRepository.save(user);
        }
        return user;
    }
}
