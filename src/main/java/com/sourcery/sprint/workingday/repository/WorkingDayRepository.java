package com.sourcery.sprint.workingday.repository;

import com.sourcery.sprint.workingday.model.WorkingDayEntity;
import com.sourcery.sprint.workingday.model.WorkingDayUpdateEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkingDayRepository {

    @Autowired
    private WorkingDayRepositoryMyBatis workingDayRepositoryMyBatis;

    public List<WorkingDayEntity> findWorkingDaysById(Long sprintId, Long memberId) {
        return workingDayRepositoryMyBatis.findWorkingDaysById(sprintId, memberId);
    }

    public void saveWorkingDay(WorkingDayEntity workingDayEntity) {
        workingDayRepositoryMyBatis.save(workingDayEntity);
    }

    public List<WorkingDayEntity> findInitialWorkingDaysById(Long sprintId, Long memberId) {
        return workingDayRepositoryMyBatis.findInitialWorkingDaysById(sprintId, memberId);
    }

    public void saveInitialWorkingDay(WorkingDayEntity workingDayEntity) {
        workingDayRepositoryMyBatis.saveInitial(workingDayEntity);
    }

    public void update(WorkingDayUpdateEntity workingDayEntity){
        workingDayRepositoryMyBatis.update(workingDayEntity);
    }

    public WorkingDayUpdateEntity findById(Long id) {
        return workingDayRepositoryMyBatis.findById(id);
    }
}
