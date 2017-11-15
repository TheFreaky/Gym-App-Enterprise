package ru.kpfu.itis.gymapp.services;


import ru.kpfu.itis.gymapp.dto.UserProfileDto;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserProfileService {
    UserProfileDto getUserProfile(Long id);

    UserProfileDto editUserProfile(UserProfileDto profile);
}
