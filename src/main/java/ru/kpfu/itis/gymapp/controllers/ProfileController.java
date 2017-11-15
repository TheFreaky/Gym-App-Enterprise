package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.gymapp.dto.UserProfileDto;
import ru.kpfu.itis.gymapp.forms.UserProfileForm;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.services.AuthenticationService;
import ru.kpfu.itis.gymapp.services.UserProfileService;

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

    @GetMapping
    public String getProfilePage(@ModelAttribute("model") ModelMap model, Authentication auth) {
        User user = authService.getUserByAuthentication(auth);
        UserProfileDto userProfile = userProfileService.getUserProfile(user.getId());
        model.addAttribute("userProfile", userProfile);
        return "profile";
    }

    @PostMapping
    public String editProfile(@ModelAttribute("model") ModelMap model, @Valid UserProfileForm form,
                              BindingResult errors, Authentication auth) {
        if (errors.hasErrors()) {
            model.addAttribute("profile", form);
            model.addAttribute("profileErrors", errors.getAllErrors());
            return "profile";
        } else {
            User user = authService.getUserByAuthentication(auth);
            userProfileService.editUserProfile(form, user);
            return "redirect:/setting";
        }
    }

}
