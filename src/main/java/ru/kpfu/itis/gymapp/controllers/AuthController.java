package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.gymapp.services.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 10.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Controller
public class AuthController {

    @Autowired
    private AuthenticationService service;

    @GetMapping("/login")
    public String login(@ModelAttribute("model") ModelMap model, Authentication authentication,
                        @RequestParam Optional<String> error) {
        if (authentication != null) {
            return "redirect:/";
        }
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Authentication authentication) {
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }

    @GetMapping("/")
    public String root(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }
            return "welcome";
    }

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        model.addAttribute(service.getUserByAuthentication(authentication));
        return "profile";
    }
}