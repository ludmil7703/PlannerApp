package com.plannerapp.controllers;

import com.plannerapp.model.TaskAddBindingModel;
import com.plannerapp.services.LoggedUser;
import com.plannerapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final LoggedUser loggedUser;

    private final TaskService taskService;

    public TaskController(LoggedUser loggedUser, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        if(!loggedUser.isLogged()){
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("task-add");
    }

    @PostMapping("/add")
    public ModelAndView add(TaskAddBindingModel taskAddBindingModel){
        if(!loggedUser.isLogged()){
            return new ModelAndView("redirect:/login");
        }

        boolean isCreated =  taskService.create(taskAddBindingModel);
        String view = isCreated ? "redirect:/home" : "redirect:/tasks/add";
        return new ModelAndView(view);
    }

    @GetMapping("/assign/{id}")
    public ModelAndView assign(@PathVariable("id") Long id){
        if(!loggedUser.isLogged()){
            return new ModelAndView("redirect:/login");
        }
        this.taskService.assign(id);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        if(!loggedUser.isLogged()){
            return new ModelAndView("redirect:/login");
        }
        this.taskService.delete(id);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/move/{id}")
    public ModelAndView move(@PathVariable("id") Long id){
        if(!loggedUser.isLogged()){
            return new ModelAndView("redirect:/login");
        }
        this.taskService.move(id);
        return new ModelAndView("redirect:/home");
    }
}
