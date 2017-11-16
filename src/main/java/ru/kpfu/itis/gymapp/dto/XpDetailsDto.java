package ru.kpfu.itis.gymapp.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 16.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
public class XpDetailsDto {
    private Integer lvl;
    private Long xpToLvlUp;
    private Integer progress;
}
