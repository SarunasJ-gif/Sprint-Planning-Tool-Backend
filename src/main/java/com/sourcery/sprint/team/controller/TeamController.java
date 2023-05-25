package com.sourcery.sprint.team.controller;

import com.sourcery.sprint.members.model.Member;
import com.sourcery.sprint.security.CustomUserEmail;
import com.sourcery.sprint.team.model.Team;
import com.sourcery.sprint.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping()
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public Iterable<Team> getTeamByUser() {
            String userEmail = CustomUserEmail.getUserEmailFromContext();
            return teamService.getAllTeamsByUserEmail(userEmail);
    }

    @DeleteMapping("/{teamId}/member/{memberId}")
    public ResponseEntity<?> deleteMemberFromTeam(@PathVariable Long teamId, @PathVariable Long memberId) {
        teamService.deleteMemberFromTeam(teamId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Team> updateTeamName(@RequestBody Team team) {
        teamService.updateTeamName(team);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping("/{teamId}/member")
    public ResponseEntity<?> addMemberToTeam(@PathVariable Long teamId, @RequestBody Member member) {
        teamService.addMemberToTeam(teamId, member);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/1")
    public ResponseEntity<Team> getTop1Team() {
        String userEmail = CustomUserEmail.getUserEmailFromContext();
        Team team = teamService.getTop1Team(userEmail);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

}
