package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gymapp.forms.UserPhoneForm;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.services.AuthenticationService;
import ru.kpfu.itis.gymapp.services.external.SmsVerificationService;
import ru.kpfu.itis.gymapp.validators.UserPhoneFromValidator;

import javax.validation.Valid;

/**
 * 25.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Controller
@RequestMapping("/setting/phone")
public class PhoneController {
    @Autowired
    private SmsVerificationService verificationService;

    @Autowired
    private UserPhoneFromValidator validator;

    @InitBinder("phoneForm")
    public void initPhoneFormValidator(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public String getPhonePage(@ModelAttribute("currentUser") User user, ModelMap model, Authentication auth) {
        model.addAttribute("phoneForm",
                UserPhoneForm.builder()
                .phone(user.getPhone())
                .build()
        );
        return "phone";
    }

    @PostMapping
    public String editPhone(@ModelAttribute("currentUser") User user, @Valid @ModelAttribute("phoneForm") UserPhoneForm form,
                            BindingResult errors) {

        if (errors.hasErrors()) {
            return "phone";
        } else {
            verificationService.makeVerification(user, form.getPhone());
            return "phoneCode";
        }
    }

    @PostMapping("/accept")
    public String confirmPhone(@RequestParam("token") String token, ModelMap model) {
        User user = verificationService.confirmVerification(token);
        if (user != null) {
            return "redirect:/setting/phone";
        } else {
            model.addAttribute("phoneError", "Wrong code");
            return "phoneCode";
        }
    }
}
