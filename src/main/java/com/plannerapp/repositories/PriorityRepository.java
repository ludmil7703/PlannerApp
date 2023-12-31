package com.plannerapp.repositories;

import com.plannerapp.model.entities.Priority;
import com.plannerapp.model.enums.PriorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, UUID> {
    Priority findByPriorityName(PriorityName name);
}
