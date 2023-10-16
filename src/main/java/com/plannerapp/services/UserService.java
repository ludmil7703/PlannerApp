package com.plannerapp.services;

import com.plannerapp.model.UserLoginBindingModel;
import com.plannerapp.model.UserRegisterBindingModel;
import com.plannerapp.model.entities.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

public interface UserService {
    
    boolean register(UserRegisterBindingModel userRegisterBindingModel);


    void login(UserLoginBindingModel userLoginBindingModel);


    void logout();

    boolean isLogged();

    User findUserByEmail(String username);

    User findUserByUsername(String username);

    boolean checkCredentials(String username, String password);
}
