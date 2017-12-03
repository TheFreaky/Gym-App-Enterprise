package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gymapp.dto.UserProfileDto;
import ru.kpfu.itis.gymapp.forms.UserProfileForm;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.services.AuthenticationService;
import ru.kpfu.itis.gymapp.services.UserProfileService;
import ru.kpfu.itis.gymapp.validators.UserProfileFormValidator;

import javax.validation.Valid;

/**
 * 15.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private UserProfileFormValidator validator;

    @InitBinder("profileForm")
    public void initProfileFormValidator(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public String getProfilePage(ModelMap model, Authentication auth) {
        User user = authService.getUserByAuthentication(auth);
        UserProfileDto userProfile = userProfileService.getUserProfile(user);
        model.addAttribute("userProfile", userProfile);
        return "profile";
    }

    @PostMapping
    public String editProfile(ModelMap model, @Valid @ModelAttribute("profileForm") UserProfileForm form,
                              BindingResult errors, Authentication auth) {
        User user = authService.getUserByAuthentication(auth);
        if (errors.hasErrors()) {
            UserProfileDto userProfile = userProfileService.getUserProfile(user);
            model.addAttribute("userProfile", userProfile);
            model.addAttribute("errors", errors.getAllErrors());
            return "profile";
        } else {
            userProfileService.editUserProfile(form, user);
            return "redirect:/profile";
        }
    }

}
