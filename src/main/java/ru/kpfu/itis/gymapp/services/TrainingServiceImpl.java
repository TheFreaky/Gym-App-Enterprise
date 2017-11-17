package ru.kpfu.itis.gymapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.dto.CompleteTrainingForm;
import ru.kpfu.itis.gymapp.dto.TrainingDto;
import ru.kpfu.itis.gymapp.models.Training;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.models.UserTraining;
import ru.kpfu.itis.gymapp.repositories.TrainingRepository;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.repositories.UserTrainingRepository;

import java.util.Date;
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
    @Autowired
    private UserTrainingRepository userTrainingRepository;

    @Override
    public List<TrainingDto> getTrainings(User user) {
        return trainingToTrainingDto(user, trainingRepository::findAllByMinLvlLessThan);
    }

    @Override
    public List<TrainingDto> getTrainingsSortedBy(String sortType, User user) {
        if ("type".equals(sortType)) {
            return getTrainingsSortedByType(user);
        } else if ("complexity".equals(sortType)) {
            return getTrainingsSortedByComplexity(user);
        }
        return null;
    }

    @Override
    public Training getTraining(String name, User user) {
        Training training = trainingRepository.findByName(name);
        if (training == null) {
            return null;
        }

        if (training.getMinLvl() > userLevelService.getLvl(user.getXp())) {
            training = null;
        }
        return training;
    }

    @Override
    public void addUserTraining(User user, CompleteTrainingForm trainingDto) {
        Training training = trainingRepository.findByName(trainingDto.getName());

        Integer doneEx = trainingDto.getExercises().length;
        Long xp = training.getXp() * doneEx / training.getExercises().size();

        userRepository.updateXp(user.getId(), xp);
        userTrainingRepository.save(
                UserTraining.builder()
                        .date(new Date())
                        .user(user)
                        .training(training)
                        .build()
        );
    }


    private List<TrainingDto> trainingToTrainingDto (User user, Function<Integer, List<Training>> function) {
        Integer lvl = userLevelService.getLvl(userRepository.findOne(user.getId()).getXp());
        return function.apply(lvl).stream()
                .map(training -> TrainingDto.builder()
                        .name(training.getName())
                        .complexity(training.getComplexity())
                        .type(training.getType())
                        .build())
                .collect(Collectors.toList());
    }

    private List<TrainingDto> getTrainingsSortedByType(User user) {
        return trainingToTrainingDto(user, trainingRepository::findAllByMinLvlLessThanOrderByType);
    }

    private List<TrainingDto> getTrainingsSortedByComplexity(User user) {
        return trainingToTrainingDto(user, trainingRepository::findAllByMinLvlLessThanOrderByComplexity);
    }
}
