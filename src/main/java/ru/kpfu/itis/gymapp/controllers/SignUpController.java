package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gymapp.forms.UserRegistrationForm;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.services.AuthenticationService;
import ru.kpfu.itis.gymapp.services.RegistrationService;
import ru.kpfu.itis.gymapp.services.external.EmailVerificationService;
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
    private AuthenticationService authService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private EmailVerificationService verificationService;

    @Autowired
    private UserRegistrationFormValidator userRegistrationFormValidator;

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
                         BindingResult errors) {
        if (errors.hasErrors()) {
            return "welcome";
        } else {
            User register = registrationService.register(userForm);
            verificationService.makeVerification(register, userForm.getLogin());

            return "redirect:/";
        }
    }

    @GetMapping("/accept")
    public String confirmRegistration(@RequestParam("token") String token) {
        User user = verificationService.confirmVerification(token);
        if (user != null) {
            authService.autoLogin(user.getLogin(), user.getPassword());
            return "redirect:/trainings";
        } else {
            return "redirect:/";
        }
    }
}