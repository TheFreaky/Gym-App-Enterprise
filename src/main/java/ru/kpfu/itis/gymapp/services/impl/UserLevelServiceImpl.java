package ru.kpfu.itis.gymapp.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.gymapp.dto.XpDetailsDto;
import ru.kpfu.itis.gymapp.services.UserLevelService;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class UserLevelServiceImpl implements UserLevelService {
    @Override
    public Integer getLvl(Long xp) {
        return (int) ((Math.sqrt(625 + 100 * xp) - 25) / 50);
    }

    @Override
    public Long getXpForLvl(Integer lvl) {
        return (long) (25 * lvl * (1 + lvl));
    }

    @Override
    public XpDetailsDto getXpDetails(Long xp) {
        Integer lvl = getLvl(xp);
        Long xpToCurrentLvl = getXpForLvl(lvl);
        Long xpToLvlUp = getXpForLvl(lvl + 1);
        Integer progress = Math.toIntExact((xp - xpToCurrentLvl) * 100 / (xpToLvlUp - xpToCurrentLvl));

        return XpDetailsDto.builder()
                .lvl(lvl)
                .xpToLvlUp(xpToLvlUp)
                .progress(progress)
                .build();
    }

    @Override
    public XpDetailsDto getSkillsXpDetails(Short xp) {
        return getXpDetails((long) (xp / 10));
    }


}
