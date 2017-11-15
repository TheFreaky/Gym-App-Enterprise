package ru.kpfu.itis.gymapp.forms;

import lombok.Builder;
import lombok.Data;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
public class UserRegistrationForm {
    private String name;
    private String password;
    private String login;
}
