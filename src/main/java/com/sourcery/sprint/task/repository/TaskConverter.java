package com.sourcery.sprint.task.repository;

import com.sourcery.sprint.task.model.Task;
import com.sourcery.sprint.task.model.TaskEntity;

public class TaskConverter {

    public static Task convertToTask(TaskEntity taskEntity) {
        Task task = new Task();
        task.setId(taskEntity.getId());
        task.setKeyValue(taskEntity.getKeyValue());
        task.setKeyColor(taskEntity.getKeyColor());
        task.setDescription(taskEntity.getDescription());
        task.setType(taskEntity.getType());
        task.setOldPoints(taskEntity.getOldPoints());
        task.setRemainingPoints(taskEntity.getRemainingPoints());
        task.setNewPoints(taskEntity.getNewPoints());
        return task;
    }

    public static TaskEntity convertToTaskEntity(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setKeyValue(task.getKeyValue());
        taskEntity.setKeyColor(task.getKeyColor());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setType(task.getType());
        taskEntity.setOldPoints(task.getOldPoints());
        taskEntity.setRemainingPoints(task.getRemainingPoints());
        taskEntity.setNewPoints(task.getNewPoints());
        return taskEntity;
    }
}

