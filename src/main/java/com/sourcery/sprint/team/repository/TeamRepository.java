package com.sourcery.sprint.team.repository;

import com.sourcery.sprint.team.model.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository {

    @Autowired
    private TeamRepositoryMyBatis teamRepositoryMyBatis;

    public void saveTeam(TeamEntity teamEntity) {
        teamRepositoryMyBatis.saveTeam(teamEntity);
    }

    public List<TeamEntity> getAllTeamsByMemberId(Long id) {
        return teamRepositoryMyBatis.findAllTeamsByMemberId(id);
    }

    public void saveTeamAndMemberIds(Long teamId, Long memberId) {
        teamRepositoryMyBatis.saveTeamAndMemberIds(teamId, memberId);
    }

    public Optional<TeamEntity> findTeamById(Long id) {
        return Optional.ofNullable(teamRepositoryMyBatis.findTeamById(id));
    }

    public void updateTeam(TeamEntity teamEntity) {
        teamRepositoryMyBatis.updateTeam(teamEntity);
    }

    public void deleteMemberFromTeam(Long teamId, Long memberId) {
        teamRepositoryMyBatis.deleteMemberFromTeam(teamId, memberId);
    }

    public void updateTeamName(String name, Long id) {
        teamRepositoryMyBatis.updateTeamName(name, id);
    }

    public TeamEntity findTop1TeamByMemberId(Long id) {
        return teamRepositoryMyBatis.findTop1TeamByMemberId(id);
    }
}
