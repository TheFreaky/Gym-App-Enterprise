package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gymapp.dto.UserProfileDto;
import ru.kpfu.itis.gymapp.forms.UserProfileForm;
import ru.kpfu.itis.gymapp.services.AdminService;
import ru.kpfu.itis.gymapp.validators.UserProfileFormValidator;

import javax.validation.Valid;

/**
 * 26.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Controller
@RequestMapping("/admin/users")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserProfileFormValidator profileFormValidator;

    @InitBinder("profileForm")
    public void initProfileFormValidator(WebDataBinder binder) {
        binder.addValidators(profileFormValidator);
    }

    @GetMapping("/profile/{id}")
    public String getUserPage(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("userProfile", adminService.getUserProfile(id));
        return "profile";
    }

    @GetMapping
    public String getUsersPage(ModelMap model) {
        model.addAttribute("users",adminService.getUsers());
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String getUserEditPage(@PathVariable("id") Long id) {
        return "redirect:/admin/users/profile/" + id;
    }

    @PostMapping("/edit/{id}")
    public String editUserProfile(@Valid @ModelAttribute("profileForm") UserProfileForm form,
                                  @PathVariable("id") Long id, BindingResult errors) {
        if (errors.hasErrors()) {
            return "profile";
        } else {
            adminService.editUserProfile(form, id);
            return "redirect:/admin/users/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        adminService.deleteUserProfile(id);
        return "redirect:/admin/users";
    }
}
