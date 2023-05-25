package com.sourcery.sprint.team.repository;

import com.sourcery.sprint.team.model.TeamEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface TeamRepositoryMyBatis {

    @Insert("INSERT INTO team (name, completed_projects, completed_tasks) " +
            "VALUES (#{name}, #{completedProjects}, #{completedTasks})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveTeam(@RequestBody TeamEntity teamEntity);

    @Insert("INSERT INTO team_member (team_id, member_id) SELECT #{teamId}, #{memberId} " +
            " WHERE NOT EXISTS ( SELECT #{teamId},  #{memberId} " +
            " FROM team_member tm WHERE tm.team_id = #{teamId} AND tm.member_id = #{memberId} )")
    void saveTeamAndMemberIds(@Param("teamId") Long teamId, @Param("memberId") Long memberId);

    @Select("SELECT t.id, t.name, t.completed_projects AS completedProjects, t.completed_tasks AS completedTasks" +
            " FROM team t LEFT JOIN team_member tm " +
            "ON t.id = tm.team_id WHERE tm.member_id = #{id} ")
    List<TeamEntity> findAllTeamsByMemberId(@Param("id") Long id);

    @Select("SELECT t.id, t.name, t.completed_projects AS completedProjects, t.completed_tasks AS completedTasks" +
            " FROM team t WHERE t.id=#{id}")
    TeamEntity findTeamById(@Param("id") Long id);

    @Update("UPDATE team SET name=#{name}, completed_projects=#{completedProjects}, completed_tasks=#{completedTasks}" +
            " WHERE id=#{id}")
    void updateTeam(@RequestBody TeamEntity teamEntity);

    @Delete("DELETE FROM team_member WHERE team_id=#{teamId} AND member_id=#{memberId}")
    void deleteMemberFromTeam(@Param("teamId") Long teamId, @Param("memberId") Long memberId);

    @Update("UPDATE team SET name = #{name} WHERE id = #{id}")
    void updateTeamName(@Param("name") String name, @Param("id") Long id);

    @Select("SELECT t.id, t.name, t.completed_projects AS completedProjects, t.completed_tasks AS completedTasks" +
            " FROM team t LEFT JOIN team_member tm " +
            "ON t.id = tm.team_id WHERE tm.member_id = #{id} ORDER BY t.id DESC LIMIT 1")
    TeamEntity findTop1TeamByMemberId(@Param("id") Long id);
}

