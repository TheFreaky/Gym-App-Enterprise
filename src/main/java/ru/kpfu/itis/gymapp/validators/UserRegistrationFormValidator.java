package ru.kpfu.itis.gymapp.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.gymapp.forms.UserRegistrationForm;
import ru.kpfu.itis.gymapp.repositories.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 10.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Component
public class UserRegistrationFormValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserRegistrationForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationForm form = (UserRegistrationForm)target;

        userRepository.findByLogin(form.getLogin()).ifPresent(user -> errors.reject("bad.login", "Email занят"));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Пустой email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Пустой пароль");

        String regex = ".+@.+\\.[a-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(form.getLogin());
        if (!matcher.matches()) {
            errors.reject("bad.login", "Неверный email");
        }

    }
}