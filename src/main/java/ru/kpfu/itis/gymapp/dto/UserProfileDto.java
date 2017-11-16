package ru.kpfu.itis.gymapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.gymapp.models.UserTraining;
import ru.kpfu.itis.gymapp.models.enums.Specialization;

import java.util.List;

/**
 * 25.09.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
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
    private Integer progress;
    private Integer strengthLvl;
    private Integer strengthProgress;
    private Integer staminaLvl;
    private Integer staminaProgress;
    private Integer flexibilityLvl;
    private Integer flexibilityProgress;
    private Boolean gender;
    private List<UserTraining> userTrainings;
}
