package ru.kpfu.itis.gymapp.services;

import ru.kpfu.itis.gymapp.forms.UserRegistrationForm;

/**
 * 10.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface SignUpService {
    void register(UserRegistrationForm userForm);
}
