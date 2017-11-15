package ru.kpfu.itis.gymapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.forms.UserProfileForm;
import ru.kpfu.itis.gymapp.repositories.UserRepository;
import ru.kpfu.itis.gymapp.repositories.UserTrainingRepository;
import ru.kpfu.itis.gymapp.dto.UserProfileDto;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.models.UserTraining;

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
    public UserProfileDto getUserProfile(Long id) {
        User user = userRepository.findOne(id);
        List<UserTraining> userTrainings = userTrainingRepository.findAllByUserId(id);

        Long xp = user.getXp();
        Integer lvl = userLevelService.getLvl(xp);
        Long xpToLvlUp = userLevelService.getXpForLvl(lvl + 1);
        Long xpToCurrentLvl = userLevelService.getXpForLvl(lvl);
        long progress = (xp - xpToCurrentLvl) * 100 / (xpToLvlUp - xpToCurrentLvl);

        int strengthLvl = user.getStrength() / 100;
        int strengthProgress = user.getStrength() % 100;
        int staminaLvl = user.getStamina() / 100;
        int staminaProgress = user.getStamina() % 100;
        int flexibilityLvl = user.getFlexibility() / 100;
        int flexibilityProgress = user.getFlexibility() % 100;


        return UserProfileDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .weight(user.getWeight())
                .height(user.getHeight())
                .specialization(user.getSpecialization())
                .xp(xp)
                .progress((byte) progress)
                .lvl(lvl)
                .xpToLvlUp(xpToLvlUp)
                .strengthLvl((short) strengthLvl)
                .strengthProgress((short) strengthProgress)
                .staminaLvl((short) staminaLvl)
                .staminaProgress((short) staminaProgress)
                .flexibilityLvl((short) flexibilityLvl)
                .flexibilityProgress((short) flexibilityProgress)
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
