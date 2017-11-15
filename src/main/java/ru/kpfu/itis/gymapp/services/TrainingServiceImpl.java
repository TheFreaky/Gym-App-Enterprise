package ru.kpfu.itis.gymapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.repositories.TrainingRepository;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.dto.TrainingDto;
import ru.kpfu.itis.gymapp.dto.UserDto;
import ru.kpfu.itis.gymapp.models.Training;
import ru.kpfu.itis.gymapp.models.User;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private UserLevelService userLevelService;

    @Override
    public List<TrainingDto> getTrainings(UserDto user) {
        return trainingToTrainingDto(user, trainingRepository::findAllByMinLvlLessThan);
    }

    @Override
    public List<TrainingDto> getTrainingsSortedByType(UserDto user) {
        return trainingToTrainingDto(user, trainingRepository::findAllByMinLvlLessThanOrderByType);
    }

    @Override
    public List<TrainingDto> getTrainingsSortedByComplexity(UserDto user) {
        return trainingToTrainingDto(user, trainingRepository::findAllByMinLvlLessThanOrderByComplexity);
    }

    @Override
    public Training getTraining(String name, User userDto) {
        Training training = trainingRepository.findByName(name);
        if (training == null) {
            return null;
        }

        User user = userRepository.findOne(userDto.getId());
        if (training.getMinLvl() > userLevelService.getLvl(user.getXp())) {
            training = null;
        }
        return training;
    }

    private List<TrainingDto> trainingToTrainingDto (UserDto user, Function<Integer, List<Training>> function) {
        Integer lvl = userLevelService.getLvl(userRepository.findOne(user.getId()).getXp());
        return function.apply(lvl).stream()
                .map(training -> TrainingDto.builder()
                        .name(training.getName())
                        .complexity(training.getComplexity())
                        .type(training.getType())
                        .build())
                .collect(Collectors.toList());
    }
}
