package com.sourcery.sprint.workingday.repository;

import com.sourcery.sprint.workingday.model.WorkingDay;
import com.sourcery.sprint.workingday.model.WorkingDayUpdateEntity;


public class WorkingDayUpdateConverter {

    public static WorkingDay toWorkingDay(WorkingDayUpdateEntity workingDayEntity) {
        WorkingDay workingDay = new WorkingDay();
        workingDay.setId(workingDayEntity.getId());
        workingDay.setDay(workingDayEntity.getDay());
        workingDay.setTaskKeyValue(workingDayEntity.getTaskKeyValue());
        return workingDay;
    }

    public static WorkingDayUpdateEntity toWorkingDayEntity(WorkingDay workingDay) {
        WorkingDayUpdateEntity workingDayEntity = new WorkingDayUpdateEntity();
        workingDayEntity.setId(workingDay.getId());
        workingDayEntity.setDay(workingDay.getDay());
        workingDayEntity.setTaskKeyValue(workingDay.getTaskKeyValue());
        return workingDayEntity;
    }
}
