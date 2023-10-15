package com.plannerapp.services;

import com.plannerapp.model.TaskAddBindingModel;
import com.plannerapp.model.TaskHomeDTO;

import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    boolean create(TaskAddBindingModel taskAddBindingModel);

    void delete(Long id);

    void move(Long id);

    TaskHomeDTO getTaskForHomePage();

    void assign(Long id);
}
