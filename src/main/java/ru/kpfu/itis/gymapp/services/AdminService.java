package ru.kpfu.itis.gymapp.services;

import ru.kpfu.itis.gymapp.dto.UserProfileDto;
import ru.kpfu.itis.gymapp.forms.UserProfileForm;
import ru.kpfu.itis.gymapp.models.User;

import java.util.List;

/**
 * 30.11.2017
 *
 * @author Kuznetsov Maxim
 */
public interface AdminService {
    UserProfileDto getUserProfile(Long id);

    void editUserProfile(UserProfileForm form, Long id);

    void deleteUserProfile(Long id);

    List<User> getUsers();
}
