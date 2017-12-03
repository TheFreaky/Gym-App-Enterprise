package ru.kpfu.itis.gymapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.dto.UserProfileDto;
import ru.kpfu.itis.gymapp.dto.XpDetailsDto;
import ru.kpfu.itis.gymapp.forms.UserProfileForm;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.models.UserTraining;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.repositories.UserTrainingRepository;
import ru.kpfu.itis.gymapp.services.UserLevelService;
import ru.kpfu.itis.gymapp.services.UserProfileService;

import java.util.List;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTrainingRepository userTrainingRepository;
    @Autowired
    private UserLevelService userLevelService;

    @Override
    public UserProfileDto getUserProfile(User user) {
        List<UserTraining> userTrainings =
                userTrainingRepository.findFirst10ByUserIdOrderByDateDescIdDesc(user.getId());

        Long xp = user.getXp();
        XpDetailsDto userXpDetails = userLevelService.getXpDetails(xp);
        XpDetailsDto strengthDetails = userLevelService.getSkillsXpDetails(user.getStrength());
        XpDetailsDto staminaDetails = userLevelService.getSkillsXpDetails(user.getStamina());
        XpDetailsDto flexibilityDetails = userLevelService.getSkillsXpDetails(user.getFlexibility());

        return UserProfileDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .weight(user.getWeight())
                .height(user.getHeight())
                .specialization(user.getSpecialization())
                .xp(xp)
                .progress(userXpDetails.getProgress())
                .lvl(userXpDetails.getLvl())
                .xpToLvlUp(userXpDetails.getXpToLvlUp())
                .strengthLvl(strengthDetails.getLvl())
                .strengthProgress(strengthDetails.getProgress())
                .staminaLvl(staminaDetails.getLvl())
                .staminaProgress(staminaDetails.getProgress())
                .flexibilityLvl(flexibilityDetails.getLvl())
                .flexibilityProgress(flexibilityDetails.getProgress())
                .gender(user.getGender())
                .userTrainings(userTrainings)
                .build();
    }

    @Override
    public void editUserProfile(UserProfileForm form, User user) {
        user = userRepository.findOne(user.getId());
        user.setName(form.getName());
        user.setWeight(form.getWeight());
        user.setHeight(form.getHeight());
        user.setGender(form.getGender());
        userRepository.save(user);
    }
}
