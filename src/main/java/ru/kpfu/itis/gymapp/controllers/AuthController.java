package ru.kpfu.itis.gymapp.controllers;

import com.google.common.base.Strings;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/login")
    public String login(ModelMap model, Authentication authentication,
                        @RequestParam(required = false) String error) {
        if (authentication != null) {
            return "redirect:/";
        }
        if (!Strings.isNullOrEmpty(error)) {
            model.addAttribute("signinErrors", "Wrong email or password");
        }

        return "welcome";
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
}