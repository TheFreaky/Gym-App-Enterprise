package ru.kpfu.itis.gymapp.forms;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
public class UserProfileForm {
    @NotNull(message = "Пустое имя")
    @NotEmpty(message = "Пустое имя")
    private String name;

    @NotNull(message = "Пустой пол")
    private Boolean gender;

    @NotNull(message = "Пустой вес")
    private Float weight;

    @NotNull(message = "Пустой рост")
    private Short height;
}
