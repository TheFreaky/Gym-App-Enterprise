package ru.kpfu.itis.gymapp.services;


import ru.kpfu.itis.gymapp.dto.CompleteTrainingForm;
import ru.kpfu.itis.gymapp.dto.TrainingDto;
import ru.kpfu.itis.gymapp.models.Training;
import ru.kpfu.itis.gymapp.models.User;

import java.util.List;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface TrainingService {
    List<TrainingDto> getTrainings(User user);

    List<TrainingDto> getTrainingsSortedBy(String sortType, User user);

    Training getTraining(String name, User user);

    void addUserTraining(User user, CompleteTrainingForm trainingDto);
}
