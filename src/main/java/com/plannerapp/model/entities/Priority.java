package com.plannerapp.model.entities;

import com.plannerapp.model.enums.PriorityName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Column(name = "priority_name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PriorityName priorityName;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority", fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Priority() {
    this.tasks = new ArrayList<>();
    }

    public PriorityName getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(PriorityName priorityName) {
        this.priorityName = priorityName;
        this.setDescription(priorityName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(PriorityName priorityName) {
        switch (priorityName){
            case URGENT:
                this.description = "An urgent problem that blocks the system use until the issue is resolved.";
                break;
            case IMPORTANT:
                this.description = "A Core functionality that your product is explicitly supposed to perform is compromised.";
                break;
            case LOW:
                this.description = "Should be fixed if time permits but can be postponed.";
                break;
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
