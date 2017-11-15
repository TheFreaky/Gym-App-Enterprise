package ru.kpfu.itis.gymapp.services;


import ru.kpfu.itis.gymapp.dto.UserProfileDto;
import ru.kpfu.itis.gymapp.forms.UserProfileForm;
import ru.kpfu.itis.gymapp.models.User;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserProfileService {
    UserProfileDto getUserProfile(Long id);

    void editUserProfile(UserProfileForm form, User user);
}
