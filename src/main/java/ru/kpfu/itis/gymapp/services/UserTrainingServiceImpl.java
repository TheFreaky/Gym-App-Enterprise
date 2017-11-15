package ru.kpfu.itis.gymapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.repositories.TrainingRepository;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.repositories.UserTrainingRepository;
import ru.kpfu.itis.gymapp.dto.UserDto;
import ru.kpfu.itis.gymapp.models.Training;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.models.UserTraining;

import java.time.LocalDate;
import java.util.List;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class UserTrainingServiceImpl implements UserTrainingService {
    @Autowired
    private UserTrainingRepository userTrainingRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserTraining> getUserTrainings(UserDto user) {
        return userTrainingRepository.findAllByUserId(user.getId());
    }

    @Override
    public void addUserTraining(UserDto userDto, String trainingName, Integer doneEx) {
        Training training = trainingRepository.findByName(trainingName);
        User user = User.builder()
                .id(userDto.getId())
                .build();

        Long xp = training.getXp() * doneEx / training.getExercises().size();

        userRepository.updateXp(userDto.getId(), xp);
        userTrainingRepository.save(
                UserTraining.builder()
                        .date(LocalDate.now())
                        .user(user)
                        .training(training)
                        .build()
        );
    }
}
