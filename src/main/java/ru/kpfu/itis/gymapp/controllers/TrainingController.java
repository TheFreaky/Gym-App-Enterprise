package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gymapp.dto.TrainingDto;
import ru.kpfu.itis.gymapp.dto.UserDto;
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
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @Autowired
    private AuthenticationService authService;

    @GetMapping
    public String getTrainingsPage() {
        return "trainings";
    }

    @GetMapping
    public String getTrainingPage(@ModelAttribute("model") ModelMap model,
                                  @RequestParam("name") String name, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        model.addAttribute("training", trainingService.getTraining(name, user));
        return "training";
    }



    private List<TrainingDto> getTrainings(String sort, UserDto userDto) {
        if ("type".equals(sort)) {
            return trainingService.getTrainingsSortedByType(userDto);
        } else if ("complexity".equals(sort)) {
            return trainingService.getTrainingsSortedByComplexity(userDto);
        } else {
            return trainingService.getTrainings(userDto);
        }
    }
}
