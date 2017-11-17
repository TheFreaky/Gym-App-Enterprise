package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gymapp.forms.UserRegistrationForm;
import ru.kpfu.itis.gymapp.services.AuthenticationService;
import ru.kpfu.itis.gymapp.services.UserService;
import ru.kpfu.itis.gymapp.validators.UserRegistrationFormValidator;

import javax.validation.Valid;

/**
 * 09.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Controller
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRegistrationFormValidator userRegistrationFormValidator;

    @Autowired
    private AuthenticationService authService;

    @InitBinder("userForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(userRegistrationFormValidator);
    }


    @GetMapping
    public String getSignUpPage() {
        return "redirect:/";
    }

    @PostMapping
    public String signUp(@Valid @ModelAttribute("userForm") UserRegistrationForm userForm,
                         BindingResult errors, ModelMap model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", userForm);
            model.addAttribute("errors", errors.getAllErrors());
            return "welcome";
        } else {
            userService.register(userForm);
            authService.autologin(userForm.getLogin(), userForm.getPassword());
            return "redirect:/trainings";
        }
    }
}
