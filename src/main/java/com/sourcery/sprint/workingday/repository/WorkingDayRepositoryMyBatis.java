package com.sourcery.sprint.workingday.repository;

import com.sourcery.sprint.workingday.model.WorkingDayEntity;
import com.sourcery.sprint.workingday.model.WorkingDayUpdateEntity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface WorkingDayRepositoryMyBatis {

    @Insert("INSERT INTO working_day (day, sprint_id, member_id, task_key_value) " +
            "VALUES (#{day}, #{sprintId}, #{memberId}, #{taskKeyValue})")
    void save(@RequestBody WorkingDayEntity workingDayEntity);

    @Select("SELECT id, day, sprint_id AS sprintId, member_id AS memberId, task_key_value AS taskKeyValue" +
            " FROM working_day WHERE sprint_id=#{sprintId} AND member_id=#{memberId}")
    List<WorkingDayEntity> findWorkingDaysById(@Param("sprintId") Long sprintId, @Param("memberId") Long memberId);

    @Insert("INSERT INTO initial_working_day (day, sprint_id, member_id, task_key_value) " +
    "VALUES (#{day}, #{sprintId}, #{memberId}, #{taskKeyValue})")
        void saveInitial(@RequestBody WorkingDayEntity workingDayEntity);

    @Select("SELECT id, day, sprint_id AS sprintId, member_id AS memberId, task_key_value AS taskKeyValue" +
    " FROM initial_working_day WHERE sprint_id=#{sprintId} AND member_id=#{memberId}")
    List<WorkingDayEntity> findInitialWorkingDaysById(@Param("sprintId") Long sprintId, @Param("memberId") Long memberId);

    @Update("UPDATE working_day SET task_key_value=#{taskKeyValue} " +
    "WHERE id=#{id}")
    void update(@RequestBody WorkingDayUpdateEntity workingDayEntity);

    @Select("SELECT m.id, m.sprint_id AS sprintId, m.member_id AS memberId, m.task_key_value AS taskKeyValue " +
    "FROM working_day m WHERE m.id=#{id}")
    WorkingDayUpdateEntity findById(@Param("id") Long id);
}


