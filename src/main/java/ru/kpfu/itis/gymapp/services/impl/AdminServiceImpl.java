package ru.kpfu.itis.gymapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.dto.UserProfileDto;
import ru.kpfu.itis.gymapp.forms.UserProfileForm;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.models.enums.State;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.services.AdminService;
import ru.kpfu.itis.gymapp.services.UserProfileService;

import java.util.List;

/**
 * 30.11.2017
 *
 * @author Kuznetsov Maxim
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserProfileDto getUserProfile(Long id) {
        return userProfileService.getUserProfile(userRepository.findOne(id));
    }

    @Override
    public void editUserProfile(UserProfileForm form, Long id) {
        userProfileService.editUserProfile(form, userRepository.findOne(id));
    }

    @Override
    public void deleteUserProfile(Long id) {
        User user = userRepository.findOne(id);
        user.setState(State.DELETED);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        return users;
    }


}
