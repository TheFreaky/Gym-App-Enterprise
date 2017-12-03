package ru.kpfu.itis.gymapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.forms.UserSettingForm;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.services.UserSettingService;

/**
 * 24.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class UserSettingServiceImpl implements UserSettingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void editUserData(UserSettingForm form, User user) {
        String hashPassword = passwordEncoder.encode(form.getPassword());
        user.setLogin(form.getLogin().toLowerCase());
        if (form.getPassword() != null && !form.getPassword().isEmpty()) {
            user.setPassword(hashPassword);
        }
        userRepository.save(user);
    }
}
