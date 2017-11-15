package ru.kpfu.itis.gymapp.services;

import ru.kpfu.itis.gymapp.forms.UserRegistrationForm;
import ru.kpfu.itis.gymapp.forms.UserSettingForm;
import ru.kpfu.itis.gymapp.models.User;

/**
 * 10.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserService {
    void register(UserRegistrationForm userForm);

    void editUserData(UserSettingForm form, User user);
}
