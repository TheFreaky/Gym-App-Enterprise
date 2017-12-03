package ru.kpfu.itis.gymapp.services;

import ru.kpfu.itis.gymapp.forms.UserRegistrationForm;
import ru.kpfu.itis.gymapp.models.User;

/**
 * 24.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface RegistrationService {
    User register(UserRegistrationForm userForm);
}
