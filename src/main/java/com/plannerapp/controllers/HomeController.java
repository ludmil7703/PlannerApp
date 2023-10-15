package com.plannerapp.controllers;

import com.plannerapp.model.TaskHomeDTO;
import com.plannerapp.util.LoggedUser;
import com.plannerapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;

    private final TaskService taskService;

    public HomeController(LoggedUser loggedUser, TaskService taskService) {
        this.loggedUser = loggedUser;

        this.taskService = taskService;
    }


    @GetMapping("/")
    public ModelAndView index(){
        if (loggedUser.isLogged()){
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(){
        if(!loggedUser.isLogged()){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView("home");
        TaskHomeDTO taskHomeDTO = taskService.getTaskForHomePage();

        modelAndView.addObject("taskHomeDTO", taskHomeDTO);
        return modelAndView;
    }
}
