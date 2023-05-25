package com.sourcery.sprint.sprint.service;

import com.sourcery.sprint.exceptions.CustomExceptions;
import com.sourcery.sprint.members.model.MemberEntity;
import com.sourcery.sprint.members.model.MemberWithDays;
import com.sourcery.sprint.members.repository.MemberConverter;
import com.sourcery.sprint.members.repository.MemberRepository;
import com.sourcery.sprint.sprint.model.Sprint;
import com.sourcery.sprint.sprint.model.SprintEntity;
import com.sourcery.sprint.sprint.repository.SprintConverter;
import com.sourcery.sprint.sprint.repository.SprintRepository;
import com.sourcery.sprint.task.model.DefaultTask;
import com.sourcery.sprint.task.model.EducationTask;
import com.sourcery.sprint.task.model.Task;
import com.sourcery.sprint.task.model.TaskEntity;
import com.sourcery.sprint.task.repository.TaskConverter;
import com.sourcery.sprint.task.repository.TaskRepository;
import com.sourcery.sprint.workingday.model.WorkingDay;
import com.sourcery.sprint.workingday.model.WorkingDayEntity;
import com.sourcery.sprint.workingday.repository.WorkingDayConverter;
import com.sourcery.sprint.workingday.repository.WorkingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private WorkingDayRepository workingDayRepository;

    public void addSprint(Sprint sprint) {
        SprintEntity sprintEntity = SprintConverter.convertToSprintEntity(sprint);
        sprintRepository.saveSprint(sprintEntity);
        long savedSprintId = sprintEntity.getId();
        List<TaskEntity> taskEntities = sprint.getTasks().stream().map(TaskConverter::convertToTaskEntity).collect(Collectors.toList());
        if (taskEntities.isEmpty()) {
            throw new CustomExceptions.TaskNotAddedException();
        }
        for (TaskEntity task : taskEntities) {
            task.setSprintId(savedSprintId);
            taskRepository.saveTask(task);
        }
        List<MemberWithDays> members = sprint.getMembers();
        if (members.isEmpty()) {
            throw new CustomExceptions.MemberNotAddedException();
        }
        for (MemberWithDays member : members) {
            memberRepository.addSprintMemberIds(savedSprintId, member.getId());
            List<WorkingDay> workingDays = member.getWorkingDays();
            if (workingDays.isEmpty()) {
                throw new CustomExceptions.WorkingDaysNotAddedException();
            }
            for (WorkingDay workingDay : workingDays) {
                WorkingDayEntity workingDayEntity = WorkingDayConverter.toWorkingDayEntity(workingDay);
                workingDayEntity.setSprintId(savedSprintId);
                workingDayEntity.setMemberId(member.getId());
                workingDayRepository.saveWorkingDay(workingDayEntity);
                workingDayRepository.saveInitialWorkingDay(workingDayEntity);
            }
        }
    }

    public Sprint findSprintById(Long id) {
        Optional<SprintEntity> optionalSprintEntity = Optional.ofNullable(sprintRepository.findById(id));
        if (optionalSprintEntity.isEmpty()) {
            throw new CustomExceptions.SprintNotFoundException();
        }
        SprintEntity sprintEntity = optionalSprintEntity.get();
        List<Task> tasks = taskRepository.findBySprintId(sprintEntity.getId())
                .stream().map(TaskConverter::convertToTask).collect(Collectors.toList());
        List<MemberWithDays> members = memberRepository.findMembersBySprintId(sprintEntity.getId())
                .stream().map(MemberConverter::fromEntityToMemberWithDays).collect(Collectors.toList());
        for (MemberWithDays member : members) {
            List<WorkingDayEntity> workingDayEntities = workingDayRepository.findWorkingDaysById(sprintEntity.getId(), member.getId());
            List<WorkingDay> workingDays = new ArrayList<>();
            for (WorkingDayEntity workingDayEntity : workingDayEntities) {
                WorkingDay workingDay = WorkingDayConverter.toWorkingDay(workingDayEntity);
                if (workingDayEntity.getTaskKeyValue().equals("Education")) {
                    workingDay.setTask(new EducationTask());
                } else {
                    Optional<TaskEntity> taskEntityOptional = Optional
                            .ofNullable(taskRepository.getByKeyValue(workingDayEntity.getTaskKeyValue(), sprintEntity.getId()));
                    if (taskEntityOptional.isEmpty()) {
                        workingDay.setTask(new DefaultTask());
                    } else {
                        Task task = TaskConverter.convertToTask(taskEntityOptional.get());
                        workingDay.setTask(task);
                    }
                }
                workingDays.add(workingDay);
            }
            member.setWorkingDays(workingDays);
        }
        Sprint sprint = SprintConverter.convertToSprint(sprintEntity);
        sprint.setTasks(tasks);
        sprint.setMembers(members);
        return sprint;
    }

    public Sprint findInitialSprintById(Long id) {
        Optional<SprintEntity> optionalSprintEntity = Optional.ofNullable(sprintRepository.findById(id));
        if (optionalSprintEntity.isEmpty()) {
            throw new CustomExceptions.SprintNotFoundException();
        }
        SprintEntity sprintEntity = optionalSprintEntity.get();
        List<Task> tasks = taskRepository.findBySprintId(sprintEntity.getId())
                .stream().map(TaskConverter::convertToTask).collect(Collectors.toList());
        List<MemberWithDays> members = memberRepository.findMembersBySprintId(sprintEntity.getId())
                .stream().map(MemberConverter::fromEntityToMemberWithDays).collect(Collectors.toList());
        for (MemberWithDays member : members) {
            List<WorkingDayEntity> workingDayEntities = workingDayRepository.findInitialWorkingDaysById(sprintEntity.getId(), member.getId());
            List<WorkingDay> workingDays = new ArrayList<>();
            for (WorkingDayEntity workingDayEntity : workingDayEntities) {
                WorkingDay workingDay = WorkingDayConverter.toWorkingDay(workingDayEntity);
                if (workingDayEntity.getTaskKeyValue().equals("Education")) {
                    workingDay.setTask(new EducationTask());
                } else {
                    Optional<TaskEntity> taskEntityOptional = Optional
                            .ofNullable(taskRepository.getByKeyValue(workingDayEntity.getTaskKeyValue(), sprintEntity.getId()));
                    if (taskEntityOptional.isEmpty()) {
                        workingDay.setTask(new DefaultTask());
                    } else {
                        Task task = TaskConverter.convertToTask(taskEntityOptional.get());
                        workingDay.setTask(task);
                    }
                }
                workingDays.add(workingDay);
            }
            member.setWorkingDays(workingDays);
        }
        Sprint sprint = SprintConverter.convertToSprint(sprintEntity);
        sprint.setTasks(tasks);
        sprint.setMembers(members);
        return sprint;
    }

    public Sprint findSprintActive() {
        Optional<SprintEntity> optionalSprintEntity = Optional.ofNullable(sprintRepository.findSprintActive());
        if (optionalSprintEntity.isEmpty()) {
            throw new CustomExceptions.SprintNotFoundException();
        }
        SprintEntity sprintEntity = optionalSprintEntity.get();
        List<Task> tasks = taskRepository.findBySprintId(sprintEntity.getId())
                .stream().map(TaskConverter::convertToTask).collect(Collectors.toList());
        List<MemberWithDays> members = memberRepository.findMembersBySprintId(sprintEntity.getId())
                .stream().map(MemberConverter::fromEntityToMemberWithDays).collect(Collectors.toList());
        for (MemberWithDays member : members) {
            List<WorkingDayEntity> workingDayEntities = workingDayRepository.findWorkingDaysById(sprintEntity.getId(), member.getId());
            List<WorkingDay> workingDays = new ArrayList<>();
            for (WorkingDayEntity workingDayEntity : workingDayEntities) {
                WorkingDay workingDay = WorkingDayConverter.toWorkingDay(workingDayEntity);
                if (workingDayEntity.getTaskKeyValue().equals("Education")) {
                    workingDay.setTask(new EducationTask());
                } else {
                    Optional<TaskEntity> taskEntityOptional = Optional
                            .ofNullable(taskRepository.getByKeyValue(workingDayEntity.getTaskKeyValue(), sprintEntity.getId()));
                    if (taskEntityOptional.isEmpty()) {
                        workingDay.setTask(new DefaultTask());
                    } else {
                        Task task = TaskConverter.convertToTask(taskEntityOptional.get());
                        workingDay.setTask(task);
                    }
                }
                workingDays.add(workingDay);
            }
            member.setWorkingDays(workingDays);
        }
        Sprint sprint = SprintConverter.convertToSprint(sprintEntity);
        sprint.setTasks(tasks);
        sprint.setMembers(members);
        return sprint;
    }
    
    public Sprint findInitialSprintActive() {
        Optional<SprintEntity> optionalSprintEntity = Optional.ofNullable(sprintRepository.findSprintActive());
        if (optionalSprintEntity.isEmpty()) {
            throw new CustomExceptions.SprintNotFoundException();
        }
        SprintEntity sprintEntity = optionalSprintEntity.get();
        List<Task> tasks = taskRepository.findBySprintId(sprintEntity.getId())
                .stream().map(TaskConverter::convertToTask).collect(Collectors.toList());
        List<MemberWithDays> members = memberRepository.findMembersBySprintId(sprintEntity.getId())
                .stream().map(MemberConverter::fromEntityToMemberWithDays).collect(Collectors.toList());
        
            populateInitialTasksAndMembers(sprintEntity, tasks, members);
        
        Sprint sprint = SprintConverter.convertToSprint(sprintEntity);
        sprint.setTasks(tasks);
        sprint.setMembers(members);
        return sprint;
    }
    
    private void populateInitialTasksAndMembers(SprintEntity sprintEntity, List<Task> tasks, List<MemberWithDays> members) {
        for (MemberWithDays member : members) {
            List<WorkingDayEntity> workingDayEntities = workingDayRepository.findInitialWorkingDaysById(sprintEntity.getId(), member.getId());
            List<WorkingDay> workingDays = new ArrayList<>();
            for (WorkingDayEntity workingDayEntity : workingDayEntities) {
                WorkingDay workingDay = WorkingDayConverter.toWorkingDay(workingDayEntity);
                if (workingDayEntity.getTaskKeyValue().equals("Education")) {
                    workingDay.setTask(new EducationTask());
                } else {
                    Optional<TaskEntity> taskEntityOptional = Optional
                            .ofNullable(taskRepository.getByKeyValue(workingDayEntity.getTaskKeyValue(), sprintEntity.getId()));
                    if (taskEntityOptional.isEmpty()) {
                        workingDay.setTask(new DefaultTask());
                    } else {
                        Task task = TaskConverter.convertToTask(taskEntityOptional.get());
                        workingDay.setTask(task);
                    }
                }
                workingDays.add(workingDay);
            }
            member.setWorkingDays(workingDays);
        }
    }

    public List<Sprint> getAllSprints(String email) {
        Optional<MemberEntity> optionalMemberEntity = Optional.ofNullable(memberRepository.findByEmail(email));
        if (optionalMemberEntity.isEmpty()) {
            throw new CustomExceptions.MemberNotFoundException();
        }
        return sprintRepository.findAllSprintByMemberId(optionalMemberEntity.get().getId()).stream()
                .map(SprintConverter::convertToSprint).collect(Collectors.toList());
    }

    public void updateSprintHistorical(Long id) {
        Optional<SprintEntity> optionalSprintEntity = Optional.ofNullable(sprintRepository.findById(id));
        if (optionalSprintEntity.isEmpty()) {
            throw new CustomExceptions.SprintNotFoundException();
        }
        sprintRepository.updateSprintHistorical(id);
    }

    public void updateSprintActive(Long id) {
        Optional<SprintEntity> optionalSprintEntity = Optional.ofNullable(sprintRepository.findById(id));
        if (optionalSprintEntity.isEmpty()) {
           throw new CustomExceptions.SprintNotFoundException();
        }
        sprintRepository.disableActiveSprints();
        sprintRepository.updateSprintActive(id);
    }
}
