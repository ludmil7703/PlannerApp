package com.plannerapp.model;

import com.plannerapp.model.entities.Priority;
import com.plannerapp.model.enums.PriorityName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TaskAddBindingModel {

    @Length(min = 2, max = 50, message = "Description length must be between 2 and 50 characters.")
    private String description;

    @NotBlank(message = "Due date must be in future.")
    private String dueDate;

    @NotBlank(message = "You must select priority!")
    private PriorityName priorityName;

    public TaskAddBindingModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityName getPriority() {
        return priorityName;
    }

    public void setPriority(PriorityName priorityName) {
        this.priorityName = priorityName;
    }
}
