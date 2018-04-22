package ru.kpfu.itis.gymapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gymapp.dto.CompleteTrainingForm;
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
@RequestMapping
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @GetMapping("/training")
    public String getTrainingPage(ModelMap model,
                                  @RequestParam("name") String name,
                                  @ModelAttribute("currentUser") User user) {
        model.addAttribute("training", trainingService.getTraining(name, user));
        return "training";
    }

    @PostMapping("/training")
    public String addTraining(CompleteTrainingForm trainingDto,
                              @ModelAttribute("currentUser") User user) {
        trainingService.addUserTraining(user, trainingDto);
        return "redirect:/trainings";
    }

    @GetMapping("/trainings")
    public String getTrainingsPage(ModelMap model,
                                   @RequestParam(name = "how", required = false) String how,
                                   @ModelAttribute("currentUser") User user) {
        List<TrainingDto> trainingDtos;
        if (how == null) {
            trainingDtos = trainingService.getTrainings(user);
        } else {
            trainingDtos = trainingService.getTrainingsSortedBy(how, user);
        }

        model.addAttribute("trainings", trainingDtos);
        return "trainings";
    }

}
