package com.plannerapp.model;

import com.plannerapp.validation.annotation.UniqueEmail;
import com.plannerapp.validation.annotation.UniqueUsername;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRegisterBindingModel {

//    @UniqueUsername(message = "Username already exists!")
    @Length(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;
    @Email(message = "Email must be valid!")
    @NotBlank(message = "Email cannot be null!")
//    @UniqueEmail(message = "Email already exists!")
    private String email;
    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
