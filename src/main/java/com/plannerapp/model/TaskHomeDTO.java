package com.plannerapp.model;

import java.util.ArrayList;
import java.util.List;

public class TaskHomeDTO {

    private List<TaskAddBindingModel> myTasks;

    private List<TaskAddBindingModel> allOtherTasks;

    private long totalOtherTasks;



    public TaskHomeDTO(List<TaskAddBindingModel> myTasks, List<TaskAddBindingModel> allOtherTasks) {
        this.myTasks = new ArrayList<>(myTasks);
        this.allOtherTasks = new ArrayList<>(allOtherTasks);
        this.totalOtherTasks = allOtherTasks.size();
    }


    public List<TaskAddBindingModel> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(List<TaskAddBindingModel> myTasks) {
        this.myTasks = myTasks;
    }

    public List<TaskAddBindingModel> getAllOtherTasks() {
        return allOtherTasks;
    }

    public void setAllOtherTasks(List<TaskAddBindingModel> allOtherTasks) {
        this.allOtherTasks = allOtherTasks;
    }

    public long getTotalOtherTasks() {
        return totalOtherTasks;
    }

    public void setTotalOtherTasks(long totalOtherTasks) {
        this.totalOtherTasks = totalOtherTasks;
    }
}
