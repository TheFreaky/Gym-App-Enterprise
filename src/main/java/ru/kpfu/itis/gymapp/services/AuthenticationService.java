package ru.kpfu.itis.gymapp.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.gymapp.models.User;

/**
 * 10.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface AuthenticationService {
    User getUserByAuthentication(Authentication authentication);

    void autoLogin(String username, String password);
}
