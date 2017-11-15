package ru.kpfu.itis.gymapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.gymapp.models.enums.Specialization;
import ru.kpfu.itis.gymapp.models.UserTraining;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private Long id;
    private String name;
    private String login;
    private Float weight;
    private Short height;
    private Specialization specialization;
    private Integer lvl;
    private Long xp;
    private Long xpToLvlUp;
    private Byte progress;
    private Short strengthLvl;
    private Short strengthProgress;
    private Short staminaLvl;
    private Short staminaProgress;
    private Short flexibilityLvl;
    private Short flexibilityProgress;
    private Boolean gender;
    private List<UserTraining> userTrainings;
}
