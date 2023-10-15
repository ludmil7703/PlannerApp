package com.plannerapp.model;

import com.plannerapp.model.entities.Priority;
import com.plannerapp.model.enums.PriorityName;

import java.util.Date;

public class TaskDTO {

    private Long id;

    private Priority priority;

    private String dueDate;

    private String description;

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
