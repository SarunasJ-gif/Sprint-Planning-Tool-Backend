package com.sourcery.sprint.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {

    private Long id;
    private String keyValue;
    private String keyColor;
    private String description;
    private String type;
    private int oldPoints;
    private int remainingPoints;
    private int newPoints;
}

