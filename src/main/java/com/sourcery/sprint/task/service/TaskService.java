package com.sourcery.sprint.task.service;

import com.sourcery.sprint.task.model.Task;
import com.sourcery.sprint.task.model.TaskEntity;
import com.sourcery.sprint.task.repository.TaskConverter;
import com.sourcery.sprint.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksBySprintId(Long sprintId) {
        List<TaskEntity> taskEntities = taskRepository.findBySprintId(sprintId);
        return taskEntities.stream().map(TaskConverter::convertToTask).collect(Collectors.toList());
    }
}
