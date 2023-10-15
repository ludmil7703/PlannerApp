package com.plannerapp.services.impl;

import com.plannerapp.model.TaskAddBindingModel;
import com.plannerapp.model.TaskDTO;
import com.plannerapp.model.TaskHomeDTO;
import com.plannerapp.model.entities.Priority;
import com.plannerapp.model.entities.Task;
import com.plannerapp.model.entities.User;
import com.plannerapp.repositories.PriorityRepository;
import com.plannerapp.repositories.TaskRepository;
import com.plannerapp.repositories.UserRepository;
import com.plannerapp.util.LoggedUser;
import com.plannerapp.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final LoggedUser loggedUser;

    private final UserRepository userRepository;

    private final PriorityRepository priorityRepository;

    public TaskServiceImpl(TaskRepository taskRepository, LoggedUser loggedUser, UserRepository userRepository, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public boolean create(TaskAddBindingModel taskAddBindingModel) {

        if (taskAddBindingModel == null) {
            return false;
        }

        Priority priority = this.priorityRepository.findByPriorityName(taskAddBindingModel.getPriority());

        Task task = new Task();
        task.setUser(null);
        task.setPriority(priority);
        task.setDescription(taskAddBindingModel.getDescription());
        task.setDueDate(taskAddBindingModel.getDueDate());

        this.taskRepository.saveAndFlush(task);
        return true;
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.findById(id);
        User user = userRepository.findByUsername(loggedUser.getUsername());
        if(task != null){
            taskRepository.delete(task);
            user.getAssignedTasks().remove(task);
        }
    }

    @Override
    public void move(Long id) {
        Task task = taskRepository.findById(id);
        if(task != null){
            task.setUser(null);
            taskRepository.save(task);
        }

    }

    @Override
    public TaskHomeDTO getTaskForHomePage() {
        List<Task> tasks = taskRepository.findAll();


        List<TaskDTO> myTasks = new ArrayList<>();
        List<TaskDTO> allTasks = new ArrayList<>();


        for (Task task : tasks) {
            TaskDTO taskDTO = new TaskDTO();
            if (task.getUser() == null) {
                taskDTO.setId(task.getId());
                taskDTO.setDescription(task.getDescription());
                taskDTO.setDueDate(task.getDueDate());
                taskDTO.setPriority(task.getPriority());
                allTasks.add(taskDTO);
            } else {
                if (task.getUser().getUsername().equals(loggedUser.getUsername())) {
                    taskDTO.setId(task.getId());
                    taskDTO.setDescription(task.getDescription());
                    taskDTO.setDueDate(task.getDueDate());
                    taskDTO.setPriority(task.getPriority());
                    myTasks.add(taskDTO);
                }
            }

        }

        return new TaskHomeDTO(myTasks, allTasks);
    }

    @Override
    public void assign(Long id) {
        User user = userRepository.findByUsername(loggedUser.getUsername());
        Task task = taskRepository.findById(id);

        if(user != null){
            user.getAssignedTasks().add(task);
            task.setUser(user);
            userRepository.save(user);
            taskRepository.save(task);
        }
    }
}
