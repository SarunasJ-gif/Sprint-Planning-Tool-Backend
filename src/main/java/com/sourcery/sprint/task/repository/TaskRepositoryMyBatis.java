package com.sourcery.sprint.task.repository;

import com.sourcery.sprint.task.model.TaskEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface TaskRepositoryMyBatis {

    @Select("SELECT id, key_value AS keyValue, key_color AS keyColor, description, type," +
            " old_points AS oldPoints, remaining_points AS remainingPoints," +
            " new_points AS newPoints FROM task WHERE sprint_id=#{id}")
    List<TaskEntity> findBySprintId(@Param("id") Long id);

    @Select("SELECT id, key_value AS keyValue, key_color AS keyColor, description, type," +
            " old_points AS oldPoints, remaining_points AS remainingPoints," +
            " new_points AS newPoints FROM task WHERE key_value=#{keyValue} AND sprint_id=#{sprintId}")
    TaskEntity findByKeyValue(@Param("keyValue") String keyValue, @Param("sprintId") Long sprintId);

    @Insert("INSERT INTO task (key_value, key_color, description, type, old_points, remaining_points, new_points, sprint_id)" +
    " VALUES (#{keyValue}, #{keyColor}, #{description}, #{type}, #{oldPoints}, #{remainingPoints}, #{newPoints}, #{sprintId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(@RequestBody TaskEntity taskEntity);
}


