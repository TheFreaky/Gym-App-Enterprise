package ru.kpfu.itis.gymapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.forms.UserRegistrationForm;
import ru.kpfu.itis.gymapp.forms.UserSettingForm;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.models.enums.Role;
import ru.kpfu.itis.gymapp.repositories.UserRepository;

/**
 * 10.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void register(UserRegistrationForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        User user = User.builder()
                .name(userForm.getName())
                .login(userForm.getLogin())
                .password(hashPassword)
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }

    @Override
    public void editUserData(UserSettingForm form, User user) {
        user = userRepository.findOne(user.getId());
        String hashPassword = passwordEncoder.encode(form.getPassword());
        user.setLogin(form.getLogin().toLowerCase());
        user.setPassword(hashPassword);
        userRepository.save(user);
    }
}
