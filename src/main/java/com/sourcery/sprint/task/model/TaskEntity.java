package com.sourcery.sprint.task.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1, initialValue = 15)
    private Long id;

    @Column(name = "key_value", nullable = false)
    private String keyValue;

    @Column(name = "key_color")
    private String keyColor;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "old_points")
    private int oldPoints;

    @Column(name = "remaining_points")
    private int remainingPoints;

    @Column(name="new_points")
    private int newPoints;

    @Column(name = "sprint_id")
    private Long sprintId;
}

