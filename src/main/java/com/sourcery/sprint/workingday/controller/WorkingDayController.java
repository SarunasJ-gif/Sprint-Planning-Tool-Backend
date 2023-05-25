package com.sourcery.sprint.workingday.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourcery.sprint.workingday.model.WorkingDay;
import com.sourcery.sprint.workingday.service.WorkingDayService;

@RestController
@RequestMapping("/workingDay")
public class WorkingDayController {

    @Autowired
    private WorkingDayService workingDayService;
    
    @PutMapping("/update")
    public ResponseEntity<WorkingDay> updateWorkingDay(@RequestBody WorkingDay workingDay) {
        workingDayService.update(workingDay);
        return new ResponseEntity<>(HttpStatus.OK);
    }    
}
