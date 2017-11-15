package ru.kpfu.itis.gymapp.services;

import org.springframework.stereotype.Service;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Service
public class UserLevelServiceImpl implements UserLevelService {
    public Integer getLvl(Long xp) {
        return (int) ((Math.sqrt(625 + 100 * xp) - 25) / 50);
    }

    public Long getXpForLvl(Integer lvl) {
        return (long) (25 * lvl * (1 + lvl));
    }
}
