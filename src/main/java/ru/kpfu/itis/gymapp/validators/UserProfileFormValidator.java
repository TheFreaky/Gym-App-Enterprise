package ru.kpfu.itis.gymapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.gymapp.forms.UserProfileForm;

/**
 * 17.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Component
public class UserProfileFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserProfileForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "empty.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "empty.weight");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "height", "empty.height");
    }
}
