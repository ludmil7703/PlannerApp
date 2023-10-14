package com.plannerapp.model.entities;

import com.plannerapp.model.enums.PriorityName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Column(name = "priority_name", nullable = false, unique = true)
    private PriorityName priorityName;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;

    public Priority() {
    this.tasks = new ArrayList<>();
    }

    public PriorityName getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(PriorityName priorityName) {
        this.priorityName = priorityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
