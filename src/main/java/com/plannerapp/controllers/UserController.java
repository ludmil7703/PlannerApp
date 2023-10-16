package com.plannerapp.controllers;

import com.plannerapp.model.UserLoginBindingModel;
import com.plannerapp.model.UserRegisterBindingModel;
import com.plannerapp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(Model model){
        if (userService.isLogged()){
            return new ModelAndView("redirect:/home");
        }

        if (!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }

        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel
            , BindingResult bindingResult,
                        RedirectAttributes rAtt){
        if (bindingResult.hasErrors()){
            rAtt.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:/login";
        }

        boolean validCredentials = this.userService.checkCredentials(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (!validCredentials) {
            rAtt
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("validCredentials", false);
            return "redirect:/login";
        }

        userService.login(userLoginBindingModel);

        return "redirect:/home";

    }

    @GetMapping("/register")
    public ModelAndView register(Model model){
        if (userService.isLogged()){
            return new ModelAndView("redirect:/home");
        }

        if (!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return new ModelAndView("register");
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel
            ,BindingResult bindingResult,
                           RedirectAttributes rAtt){
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword",
                    "error.userRegisterBindingModel",
                    "Passwords must be the same.");
        }

        if (bindingResult.hasErrors()){
            rAtt.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/register";
        }

        String view = userService.register(userRegisterBindingModel) ? "redirect:/login" : "redirect:/register";

        return view;

    }

    @GetMapping("/logout")
    public ModelAndView logout(){
        userService.logout();
        return new ModelAndView("index");
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }



}
