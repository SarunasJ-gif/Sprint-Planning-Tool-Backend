package com.sourcery.sprint.sprint.repository;

import com.sourcery.sprint.sprint.model.Sprint;
import com.sourcery.sprint.sprint.model.SprintEntity;

public class SprintConverter {

    public static Sprint convertToSprint(SprintEntity sprintEntity) {
        Sprint sprint = new Sprint();
        sprint.setId(sprintEntity.getId());
        sprint.setTitle(sprintEntity.getTitle());
        sprint.setStartDate(sprintEntity.getStartDate());
        sprint.setEndDate(sprintEntity.getEndDate());
        sprint.setIsActive(sprintEntity.getIsActive());
        sprint.setIsHistorical(sprintEntity.getIsHistorical());
        return sprint;
    }

    public static SprintEntity convertToSprintEntity(Sprint sprint) {
        SprintEntity sprintEntity = new SprintEntity();
        sprintEntity.setTitle(sprint.getTitle());
        sprintEntity.setStartDate(sprint.getStartDate());
        sprintEntity.setEndDate(sprint.getEndDate());
        sprintEntity.setIsActive(sprint.getIsActive());
        sprintEntity.setIsHistorical(sprint.getIsHistorical());
        return sprintEntity;
    }
}

