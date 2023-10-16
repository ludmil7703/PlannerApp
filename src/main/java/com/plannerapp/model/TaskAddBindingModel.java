package com.plannerapp.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.plannerapp.model.enums.PriorityName;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TaskAddBindingModel {



    @Length(min = 2, max = 50, message = "Description length must be between 2 and 50 characters.")
    private String description;

@FutureOrPresent(message = "Due date must be in future.")
@DateTimeFormat(pattern = "yyyy-MM-dd")
@NotNull(message = "Due date cannot be null.")
private Date dueDate;

@NotNull(message = "Priority cannot be null.")
private PriorityName priority;



private Long id;

    public void setPriority(PriorityName priority) {
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskAddBindingModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public void setPriorityName(PriorityName priority) {
        this.priority = priority;
    }
}
