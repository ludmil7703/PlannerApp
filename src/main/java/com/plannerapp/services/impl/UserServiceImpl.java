package com.plannerapp.services.impl;

import com.plannerapp.model.UserLoginBindingModel;
import com.plannerapp.model.UserRegisterBindingModel;
import com.plannerapp.model.entities.User;
import com.plannerapp.repositories.UserRepository;
import com.plannerapp.util.LoggedUser;
import com.plannerapp.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final LoggedUser loggedUser;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (userRegisterBindingModel == null) {
            return false;
        }


        String username = userRegisterBindingModel.getUsername();
        if (this.userRepository.findByUsername(username) != null
                || this.userRepository.findUserByEmail(userRegisterBindingModel.getEmail()) != null
           || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return false;
        }
        User user = new User();
        user.setUsername(userRegisterBindingModel.getUsername());
        user.setEmail(userRegisterBindingModel.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        this.userRepository.save(user);

        return true;
    }


    @Override
    public void login(UserLoginBindingModel userLoginBindingModel) {
        User user = this.userRepository.findByUsername(userLoginBindingModel.getUsername());
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void logout() {
        loggedUser.setUsername(null);
        loggedUser.setLogged(false);
    }

    @Override
    public boolean isLogged() {
        if (loggedUser.isLogged()) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = this.userRepository.findUserByEmail(email);

        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username);

        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = this.userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())){
            return true;
        }
        return false;
    }
}
