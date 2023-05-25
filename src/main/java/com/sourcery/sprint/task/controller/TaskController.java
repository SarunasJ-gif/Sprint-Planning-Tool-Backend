package com.sourcery.sprint.task.controller;

import com.sourcery.sprint.task.model.Task;
import com.sourcery.sprint.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public List<Task> getAllTaskBySprintId(@PathVariable Long id) {
        return taskService.getTasksBySprintId(id);
    }
}
