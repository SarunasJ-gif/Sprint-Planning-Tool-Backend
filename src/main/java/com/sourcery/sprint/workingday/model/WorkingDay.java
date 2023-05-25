package com.sourcery.sprint.workingday.model;

import com.sourcery.sprint.task.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkingDay {

    private Long id;
    private LocalDate day;
    private Task task;
    private String taskKeyValue;
}
