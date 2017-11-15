package ru.kpfu.itis.gymapp.forms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationForm {
    private String name;
    private String password;
    private String login;
}
