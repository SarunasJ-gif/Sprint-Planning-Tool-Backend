package com.sourcery.sprint.task.repository;

import com.sourcery.sprint.task.model.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    @Autowired
    private TaskRepositoryMyBatis taskRepositoryMyBatis;

    public List<TaskEntity> findBySprintId(Long id) {
        return taskRepositoryMyBatis.findBySprintId(id);
    }

    public TaskEntity getByKeyValue(String keyValue, Long sprintId) {
        return taskRepositoryMyBatis.findByKeyValue(keyValue, sprintId);
    }

    public void saveTask(TaskEntity taskEntity) {
        taskRepositoryMyBatis.save(taskEntity);
    }
}

