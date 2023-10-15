package com.plannerapp.init;

import com.plannerapp.model.entities.Priority;
import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.repositories.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PriorityInit implements CommandLineRunner {

    private final PriorityRepository priorityRepository;

    public PriorityInit(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean hasPriorities = this.priorityRepository.count() > 0;

        if (!hasPriorities) {
            List<Priority> priorities = new ArrayList<>();
            Arrays.stream(PriorityName.values())
                    .forEach(priorityName -> {
                        Priority priority = new Priority();
                        priority.setPriorityName(priorityName);

                        priorities.add(priority);
                    });
            this.priorityRepository.saveAll(priorities);
        }

    }
}
