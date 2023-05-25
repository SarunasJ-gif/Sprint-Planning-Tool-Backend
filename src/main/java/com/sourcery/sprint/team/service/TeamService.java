package com.sourcery.sprint.team.service;

import com.sourcery.sprint.exceptions.CustomExceptions;
import com.sourcery.sprint.members.model.Member;
import com.sourcery.sprint.members.model.MemberEntity;
import com.sourcery.sprint.members.repository.MemberConverter;
import com.sourcery.sprint.members.repository.MemberRepository;
import com.sourcery.sprint.team.model.Team;
import com.sourcery.sprint.team.model.TeamEntity;
import com.sourcery.sprint.team.repository.TeamConverter;
import com.sourcery.sprint.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void addTeam(Team team) {
        TeamEntity teamEntity = TeamConverter.convertToTeamEntity(team);
        Optional<TeamEntity> optionalTeamEntity = teamRepository.findTeamById(team.getId());
        if (optionalTeamEntity.isPresent()) {
            teamRepository.updateTeam(teamEntity);
        } else {
            teamRepository.saveTeam(teamEntity);
        }
        List<Member> members = team.getMembers();
        if (members.isEmpty()) {
            throw new CustomExceptions.MemberNotAddedException();
        }
        for (Member member : members) {
            teamRepository.saveTeamAndMemberIds(teamEntity.getId(), member.getId());
        }
    }

    public List<Team> getAllTeamsByUserEmail(String email) {
        Optional<MemberEntity> optionalMemberEntity = Optional.ofNullable(memberRepository.findByEmail(email));
        if (optionalMemberEntity.isEmpty()) {
            throw new CustomExceptions.MemberNotFoundException();
        }
        List<TeamEntity> teamEntities = teamRepository.getAllTeamsByMemberId(optionalMemberEntity.get().getId());
        List<Team> teams = teamEntities.stream().map(TeamConverter::convertToTeam).collect(Collectors.toList());
        for (Team team : teams) {
            List<Member> members = memberRepository.getAllMembersByTeamId(team.getId())
                    .stream().map(MemberConverter::fromEntityToMember).collect(Collectors.toList());
            team.setMembers(members);
        }
        return teams;
    }

    public void deleteMemberFromTeam(Long teamId, Long memberId) {
        Optional<TeamEntity> optionalTeamEntity = teamRepository.findTeamById(teamId);
        if (optionalTeamEntity.isEmpty()) {
            throw new CustomExceptions.TeamNotFoundException();
        }
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberId);
        if (optionalMemberEntity.isEmpty()) {
            throw new CustomExceptions.MemberNotFoundException();
        }
        teamRepository.deleteMemberFromTeam(teamId, memberId);
    }


    public void updateTeamName(Team team) {
        Optional<TeamEntity> optionalTeamEntity = teamRepository.findTeamById(team.getId());
        if (optionalTeamEntity.isEmpty()) {
            throw new CustomExceptions.TeamNotFoundException();
        }
        teamRepository.updateTeamName(team.getName(), team.getId());
    }

    public void addMemberToTeam(Long teamId, Member member) {
        Optional<TeamEntity> optionalTeamEntity = teamRepository.findTeamById(teamId);
        if (optionalTeamEntity.isEmpty()) {
            throw new CustomExceptions.TeamNotFoundException();
        }
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(member.getId());
        if (optionalMemberEntity.isEmpty()) {
            throw new CustomExceptions.MemberNotFoundException();
        }
        teamRepository.saveTeamAndMemberIds(teamId, member.getId());
        if (member.getRole() != null) {
            memberRepository.updateMemberRole(member.getRole(), member.getId());
        }
    }

    public Team getTop1Team(String userEmail) {
        Optional<MemberEntity> optionalMemberEntity = Optional.ofNullable(memberRepository.findByEmail(userEmail));
        if (optionalMemberEntity.isEmpty()) {
            throw new CustomExceptions.MemberNotFoundException();
        }
        TeamEntity teamEntity = teamRepository.findTop1TeamByMemberId(optionalMemberEntity.get().getId());
        Team team = TeamConverter.convertToTeam(teamEntity);
        List<Member> members = memberRepository.getAllMembersByTeamId(team.getId())
                .stream().map(MemberConverter::fromEntityToMember).collect(Collectors.toList());
        team.setMembers(members);
        return team;
    }
}
