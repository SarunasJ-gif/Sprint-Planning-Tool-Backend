package com.sourcery.sprint.workingday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourcery.sprint.workingday.model.WorkingDay;
import com.sourcery.sprint.workingday.model.WorkingDayUpdateEntity;
import com.sourcery.sprint.workingday.repository.WorkingDayRepository;
import com.sourcery.sprint.workingday.repository.WorkingDayUpdateConverter;

@Service
public class WorkingDayService {

    @Autowired
    private WorkingDayRepository workingDayRepository;
    
    public void update(WorkingDay workingDay) {
        WorkingDayUpdateEntity workingDayEntity = WorkingDayUpdateConverter.toWorkingDayEntity(workingDay);
        workingDayRepository.update(workingDayEntity);
    }
}
