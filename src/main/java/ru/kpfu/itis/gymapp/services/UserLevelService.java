package ru.kpfu.itis.gymapp.services;

import ru.kpfu.itis.gymapp.dto.XpDetailsDto;

/**
 * 15.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserLevelService {
    Integer getLvl(Long xp);

    Long getXpForLvl(Integer lvl);

    XpDetailsDto getXpDetails(Long xp);

    XpDetailsDto getSkillsXpDetails(Short xp);
}
