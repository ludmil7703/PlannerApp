package com.plannerapp.model;

import java.util.ArrayList;
import java.util.List;

public class TaskHomeDTO {

    private List<TaskDTO> myTasks;

    private List<TaskDTO> allOtherTasks;

    private long totalOtherTasks;



    public TaskHomeDTO(List<TaskDTO> myTasks, List<TaskDTO> allOtherTasks) {
        this.myTasks = new ArrayList<>(myTasks);
        this.allOtherTasks = new ArrayList<>(allOtherTasks);
        this.totalOtherTasks = allOtherTasks.size();
    }

    public List<TaskDTO> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(List<TaskDTO> myTasks) {
        this.myTasks = myTasks;
    }

    public List<TaskDTO> getAllOtherTasks() {
        return allOtherTasks;
    }

    public void setAllOtherTasks(List<TaskDTO> allOtherTasks) {
        this.allOtherTasks = allOtherTasks;
    }

    public long getTotalOtherTasks() {
        return totalOtherTasks;
    }

    public void setTotalOtherTasks(long totalOtherTasks) {
        this.totalOtherTasks = totalOtherTasks;
    }
}
