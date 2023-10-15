package com.plannerapp.services;

import com.plannerapp.model.UserLoginBindingModel;
import com.plannerapp.model.UserRegisterBindingModel;

public interface UserService {
    
    boolean register(UserRegisterBindingModel userRegisterBindingModel);
    
    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();

    boolean isLogged();
}
