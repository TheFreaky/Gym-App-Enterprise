package ru.kpfu.itis.gymapp.validators;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.gymapp.forms.UserSettingForm;

/**
 * 15.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserSettingFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserSettingForm.class.getName());
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserSettingForm form = (UserSettingForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Пустой логин");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Пустой пароль");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password-repeat", "empty.password-repeat", "Пустой пароль");

        if (!form.getPassword().equals(form.getPasswordRepeat())) {
            errors.reject("mismatched.passwords", "Пароли не совпадают");
        }
    }
}
