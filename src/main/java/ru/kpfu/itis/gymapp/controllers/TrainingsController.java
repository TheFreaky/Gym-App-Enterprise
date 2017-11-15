package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gymapp.dto.TrainingDto;
import ru.kpfu.itis.gymapp.models.User;
import ru.kpfu.itis.gymapp.services.AuthenticationService;
import ru.kpfu.itis.gymapp.services.TrainingService;

import java.util.List;

/**
 * 09.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Controller
@RequestMapping("/trainings")
public class TrainingsController {
    @Autowired
    private TrainingService trainingService;

    @Autowired
    private AuthenticationService authService;

    @GetMapping
    public String getTrainingsPageSort(@ModelAttribute("model") ModelMap model, Authentication auth) {
        User user = authService.getUserByAuthentication(auth);
        List<TrainingDto> trainingDtos = trainingService.getTrainings(user);

        model.addAttribute("trainings", trainingDtos);
        return "trainings";
    }

    @GetMapping
    public String getTrainingsPage(@ModelAttribute("model") ModelMap model,
                                   @RequestParam("how") String how, Authentication auth) {
        User user = authService.getUserByAuthentication(auth);
        List<TrainingDto> trainingDtos = trainingService.getTrainingsSortedBy(how, user);

        model.addAttribute("trainings", trainingDtos);
        return "trainings";
    }
}
