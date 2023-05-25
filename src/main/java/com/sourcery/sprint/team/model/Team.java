package com.sourcery.sprint.team.model;

import com.sourcery.sprint.members.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Team {

    private Long id;
    private String name;
    private Integer completedProjects;
    private Integer completedTasks;
    private List<Member> members;
}


