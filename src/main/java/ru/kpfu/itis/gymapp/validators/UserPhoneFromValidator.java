package ru.kpfu.itis.gymapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.gymapp.forms.UserPhoneForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 25.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Component
public class UserPhoneFromValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserPhoneForm.class.getName());
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserPhoneForm form = (UserPhoneForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "empty.phone");

        String phoneNumRegex = "(?:\\+7)(?:[0-9]){10}";
        Matcher matcher = Pattern.compile(phoneNumRegex).matcher(form.getPhone());
        if (!matcher.matches()) {
            errors.reject("bad.phone");
        }
    }
}
