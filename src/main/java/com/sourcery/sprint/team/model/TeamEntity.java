package com.sourcery.sprint.team.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_sequence")
    @SequenceGenerator(name = "team_sequence", sequenceName = "team_sequence", allocationSize = 1, initialValue = 2)
    private Long id;
    private String name;
    @Column(name = "completed_projects")
    private Integer completedProjects;
    @Column(name = "completed_tasks")
    private Integer completedTasks;
}
