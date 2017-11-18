package ru.kpfu.itis.gymapp.services;

import ru.kpfu.itis.gymapp.models.User;

/**
 * 18.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface VerificationService {
    void makeVerification(User user, String url);

    User confirmVerification(String token);
}
