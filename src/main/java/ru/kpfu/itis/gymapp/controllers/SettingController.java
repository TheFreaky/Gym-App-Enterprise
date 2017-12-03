package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gymapp.forms.UserSettingForm;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.services.AuthenticationService;
import ru.kpfu.itis.gymapp.services.UserSettingService;
import ru.kpfu.itis.gymapp.validators.UserSettingFormValidator;

import javax.validation.Valid;

/**
 * 15.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Controller
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    private UserSettingService userSettingService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private UserSettingFormValidator validator;

    @InitBinder("settingForm")
    public void initSettingFormValidator(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public String getSettingPage(ModelMap model, Authentication auth) {
        User user = authService.getUserByAuthentication(auth);
        model.addAttribute("setting", UserSettingForm.builder()
                .login(user.getLogin())
                .build()
        );
        return "setting";
    }

    @PostMapping
    public String editSetting(ModelMap model,
                              @Valid @ModelAttribute("settingForm") UserSettingForm form,
                              BindingResult errors, Authentication auth) {

        if (errors.hasErrors()) {
            model.addAttribute("setting", form);
            model.addAttribute("errors", errors.getAllErrors());
            return "setting";
        } else {
            userSettingService.editUserData(form, authService.getUserByAuthentication(auth));
            return "redirect:/setting";
        }
    }

}
