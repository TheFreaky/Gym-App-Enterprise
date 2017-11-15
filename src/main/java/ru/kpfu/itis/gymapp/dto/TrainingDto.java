package ru.kpfu.itis.gymapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.gymapp.models.enums.Complexity;
import ru.kpfu.itis.gymapp.models.enums.Specialization;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto {
    private String name;
    private Complexity complexity;
    private Specialization type;
}
