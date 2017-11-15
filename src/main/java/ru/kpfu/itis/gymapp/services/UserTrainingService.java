package ru.kpfu.itis.gymapp.services;


import ru.kpfu.itis.gymapp.dto.UserDto;
import ru.kpfu.itis.gymapp.models.UserTraining;

import java.util.List;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserTrainingService {
    List<UserTraining> getUserTrainings(UserDto user);

    void addUserTraining(UserDto userDto, String trainingName, Integer doneEx);
}
