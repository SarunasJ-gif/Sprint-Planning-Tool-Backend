package com.sourcery.sprint.workingday.repository;

import com.sourcery.sprint.workingday.model.WorkingDay;
import com.sourcery.sprint.workingday.model.WorkingDayEntity;


public class WorkingDayConverter {

    public static WorkingDay toWorkingDay(WorkingDayEntity workingDayEntity) {
        WorkingDay workingDay = new WorkingDay();
        workingDay.setId(workingDayEntity.getId());
        workingDay.setDay(workingDayEntity.getDay());
        workingDay.setTaskKeyValue(workingDayEntity.getTaskKeyValue());
        return workingDay;
    }

    public static WorkingDayEntity toWorkingDayEntity(WorkingDay workingDay) {
        WorkingDayEntity workingDayEntity = new WorkingDayEntity();
        workingDayEntity.setId(workingDay.getId());
        workingDayEntity.setDay(workingDay.getDay());
        if (workingDay.getTask() == null) {
            workingDayEntity.setTaskKeyValue("Default");
        } else {
            workingDayEntity.setTaskKeyValue(workingDay.getTask().getKeyValue());
        }
        return workingDayEntity;
    }
}
