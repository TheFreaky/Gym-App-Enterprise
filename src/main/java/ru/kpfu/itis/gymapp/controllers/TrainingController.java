package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.gymapp.dto.CompleteTrainingDto;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.services.AuthenticationService;
import ru.kpfu.itis.gymapp.services.TrainingService;


/**
 * 09.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Controller
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @Autowired
    private AuthenticationService authService;

    @GetMapping
    public String getTrainingPage(ModelMap model,
                                  @RequestParam("name") String name, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        model.addAttribute("training", trainingService.getTraining(name, user));
        return "training";
    }

    @PostMapping
    public String addTraining(CompleteTrainingDto trainingDto, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        trainingService.addUserTraining(user, trainingDto);
        return "redirect:/trainings";
    }

}
