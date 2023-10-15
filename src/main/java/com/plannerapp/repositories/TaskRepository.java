package com.plannerapp.repositories;

import com.plannerapp.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
List<Task> findAll();

    List<Task> findAllByUser(String username);

    Task findById(Long id);
}
