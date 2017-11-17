package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.services.AuthenticationService;

/**
 * 17.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@ControllerAdvice
public class UserControllerAdvice {
    @Autowired
    private AuthenticationService authService;

    @ModelAttribute("currentUser")
    public User getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : authService.getUserByAuthentication(authentication);
    }
}
