package com.sourcery.sprint.sprint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sourcery.sprint.members.model.MemberWithDays;
import com.sourcery.sprint.task.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sprint {

    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("startDate")
    private LocalDate startDate;
    @JsonProperty("endDate")
    private LocalDate endDate;
    @JsonProperty("isActive")
    private boolean isActive;
    @JsonProperty("isHistorical")
    private boolean isHistorical;
    private List<Task> tasks;
    private List<MemberWithDays> members;

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsHistorical(boolean IsHistorical) {
        this.isHistorical = IsHistorical;
    }

    public boolean getIsHistorical() {
        return isHistorical;
    }
}

