package ru.kpfu.itis.gymapp.validators;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.gymapp.forms.UserSettingForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 15.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Component
public class UserSettingFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserSettingForm.class.getName());
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserSettingForm form = (UserSettingForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login");

        String emailRegex = ".+@.+\\.[a-z]+";
        Matcher emailMatcher = Pattern.compile(emailRegex)
                .matcher(form.getLogin());
        if (!emailMatcher.matches()) {
            errors.reject("bad.email");
        }

        if ((form.getPassword() != null && form.getPassword().isEmpty())
                && (form.getPasswordRepeat() != null && form.getPasswordRepeat().isEmpty())
                && !form.getPassword().equals(form.getPasswordRepeat())) {
            errors.reject("mismatched.passwords");
        }
    }
}