package com.sourcery.sprint.team.repository;

import com.sourcery.sprint.team.model.Team;
import com.sourcery.sprint.team.model.TeamEntity;

public class TeamConverter {

    public static TeamEntity convertToTeamEntity(Team team) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(team.getId());
        teamEntity.setName(team.getName());
        teamEntity.setCompletedProjects(team.getCompletedProjects());
        teamEntity.setCompletedTasks(team.getCompletedTasks());
        return teamEntity;
    }

    public static Team convertToTeam(TeamEntity teamEntity) {
        Team team = new Team();
        team.setId(teamEntity.getId());
        team.setName(teamEntity.getName());
        team.setCompletedProjects(teamEntity.getCompletedProjects());
        team.setCompletedTasks(teamEntity.getCompletedTasks());
        return team;
    }
}
