package com.plannerapp.controllers;

import com.plannerapp.model.TaskAddBindingModel;
import com.plannerapp.util.LoggedUser;
import com.plannerapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    public ModelAndView add(Model model){
        if(!loggedUser.isLogged()){
            return new ModelAndView("redirect:/login");
        }
        if (!model.containsAttribute("taskAddBindingModel")){
            model.addAttribute("taskAddBindingModel", new TaskAddBindingModel());
        }
        return new ModelAndView("task-add");
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("taskAddBindingModel") TaskAddBindingModel taskAddBindingModel
            , BindingResult bindingResult,
                            RedirectAttributes rAtt){


        if (bindingResult.hasErrors()){
            rAtt.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel", bindingResult);
            return "redirect:/tasks/add";
        }
        boolean isCreated =  taskService.create(taskAddBindingModel);


        return isCreated ? "redirect:/home" : "redirect:/tasks/add";
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
