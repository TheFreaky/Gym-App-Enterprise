package ru.kpfu.itis.gymapp.services;

/**
 * 15.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserLevelService {
    Integer getLvl(Long xp);
    Long getXpForLvl(Integer lvl);
}
