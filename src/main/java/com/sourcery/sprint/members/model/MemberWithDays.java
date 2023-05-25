package com.sourcery.sprint.members.model;

import com.sourcery.sprint.workingday.model.WorkingDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberWithDays {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private Boolean softDelete;
    private List<WorkingDay> workingDays;
}
