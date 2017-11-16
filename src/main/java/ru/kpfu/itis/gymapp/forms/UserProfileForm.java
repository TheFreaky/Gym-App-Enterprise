package ru.kpfu.itis.gymapp.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileForm {
    @NotNull(message = "{empty.name}")
    @NotEmpty(message = "{empty.name}")
    private String name;

    @NotNull(message = "{empty.gender}")
    private Boolean gender;

    @NotNull(message = "{empty.weight}")
    private Float weight;

    @NotNull(message = "{empty.height}")
    private Short height;
}
