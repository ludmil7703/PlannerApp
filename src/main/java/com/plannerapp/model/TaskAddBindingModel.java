package com.plannerapp.model;

import com.plannerapp.model.entities.Priority;
import com.plannerapp.model.enums.PriorityName;

import java.util.Date;

public class TaskAddBindingModel {

    private String description;

    private String dueDate;

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
