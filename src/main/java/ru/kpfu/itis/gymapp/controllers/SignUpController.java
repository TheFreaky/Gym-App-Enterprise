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
import ru.kpfu.itis.gymapp.services.UserService;
import ru.kpfu.itis.gymapp.services.VerificationService;
import ru.kpfu.itis.gymapp.validators.UserRegistrationFormValidator;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private VerificationService verificationService;

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
                         BindingResult errors, ModelMap model, HttpServletRequest request) {
        if (errors.hasErrors()) {
            model.addAttribute("user", userForm);
            model.addAttribute("errors", errors.getAllErrors());
            return "welcome";
        } else {
            User register = userService.register(userForm);
            String url = request.getScheme() + "://" + request.getServerName() + request.getContextPath();
            verificationService.makeVerification(register, url);

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