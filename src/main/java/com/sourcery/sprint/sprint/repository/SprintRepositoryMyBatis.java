package com.sourcery.sprint.sprint.repository;

import com.sourcery.sprint.sprint.model.SprintEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface SprintRepositoryMyBatis {

    @Select("SELECT id, title, start_date AS startDate, end_date AS endDate, is_active AS isActive, is_historical AS isHistorical FROM sprint WHERE id=#{id}")
    SprintEntity findById(@Param("id") Long id);

    @Select("SELECT id, title, start_date AS startDate, end_date AS endDate, is_active AS isActive, is_historical" +
            " AS isHistorical FROM sprint WHERE is_active=true ORDER BY id DESC LIMIT 1")
    SprintEntity findActive();

    @Insert("INSERT INTO sprint (title, start_date, end_date, is_active, is_historical) VALUES (#{title}, #{startDate}, #{endDate}, #{isActive}, #{isHistorical})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(@RequestBody SprintEntity sprintEntity);

    @Select("SELECT s.id, s.title, s.start_date AS startDate, s.end_date AS endDate, s.is_active AS isActive, s.is_historical AS isHistorical FROM sprint s " +
            "LEFT JOIN sprint_member sm ON s.id = sm.sprint_id WHERE sm.member_id = #{id}")
    List<SprintEntity> getAllSprintsByMemberId(@Param("id")Long id);

    @Update("UPDATE sprint SET is_active = true, is_historical = false WHERE id = #{id}")
    void updateSprintActive(@Param("id") Long id);

    @Select("SELECT s.id, s.title, s.start_date AS startDate, s.end_date AS endDate, s.is_active AS isActive, s.is_historical AS isHistorical FROM sprint s " +
            "LEFT JOIN sprint_member sm ON s.id = sm.sprint_id WHERE sm.member_id = #{id} AND s.is_active = true")
    List<SprintEntity> getAllActiveSprintsByMemberId(@Param("id") Long id);

    @Select("SELECT s.id, s.title, s.start_date AS startDate, s.end_date AS endDate, s.is_active AS isActive, s.is_historical AS isHistorical FROM sprint s " +
            "LEFT JOIN sprint_member sm ON s.id = sm.sprint_id WHERE sm.member_id = #{id} AND s.is_historical = true")
    List<SprintEntity> getAllHistoricalSprintsByMemberId(@Param("id") Long id);

    @Update("UPDATE sprint SET is_historical = true, is_active = false WHERE id = #{id}")
    void updateSprintHistorical(@Param("id") Long id);

    @Update("UPDATE sprint SET is_active = false WHERE is_active = true")
    void disableActiveSprints();
}
