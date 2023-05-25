package com.sourcery.sprint.sprint.repository;

import com.sourcery.sprint.sprint.model.SprintEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SprintRepository {

    @Autowired
    SprintRepositoryMyBatis sprintRepositoryMyBatis;

    public SprintEntity findById(Long id) {
        return sprintRepositoryMyBatis.findById(id);
    }

    public SprintEntity findSprintActive() {
        return sprintRepositoryMyBatis.findActive();
    }

    public void saveSprint(SprintEntity sprintEntity) {
        sprintRepositoryMyBatis.save(sprintEntity);
    }

    public List<SprintEntity> findAllSprintByMemberId(Long id) {
        return sprintRepositoryMyBatis.getAllSprintsByMemberId(id);
    }

    public void updateSprintActive(Long id) {
        sprintRepositoryMyBatis.updateSprintActive(id);
    }
    
    public List<SprintEntity> findAllActiveSprintsByMemberId(Long id) {
        return sprintRepositoryMyBatis.getAllActiveSprintsByMemberId(id);
    }

    public List<SprintEntity> findAllHistoricalSprintsByMemberId(Long id) {
        return sprintRepositoryMyBatis.getAllHistoricalSprintsByMemberId(id);
    }

    public void updateSprintHistorical(Long id) {
        sprintRepositoryMyBatis.updateSprintHistorical(id);
    }

    public void disableActiveSprints() {
        sprintRepositoryMyBatis.disableActiveSprints();
    }
}
