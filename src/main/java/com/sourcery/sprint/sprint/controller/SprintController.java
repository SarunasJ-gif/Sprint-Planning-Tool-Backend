package com.sourcery.sprint.sprint.controller;

import com.sourcery.sprint.security.CustomUserEmail;
import com.sourcery.sprint.sprint.model.Sprint;
import com.sourcery.sprint.sprint.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprint")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @GetMapping("{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable Long id) {
        Sprint sprint = sprintService.findSprintById(id);
        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    @GetMapping("/initial/{id}")
    public ResponseEntity<Sprint> getInitialSprintById(@PathVariable Long id) {
        Sprint sprint = sprintService.findInitialSprintById(id);
        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sprint> addSprint(@RequestBody Sprint sprint) {
        sprintService.addSprint(sprint);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<Sprint>> getAllSprints() {
        String userEmail = CustomUserEmail.getUserEmailFromContext();
        List<Sprint> sprints = sprintService.getAllSprints(userEmail);
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<Sprint> getActiveSprint() {
        Sprint sprint = sprintService.findSprintActive();
        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    @GetMapping("/initial/active")
    public ResponseEntity<Sprint> getInitialActiveSprint() {
        Sprint sprint = sprintService.findInitialSprintActive();
        return ResponseEntity.ok(sprint);
    }

    @PutMapping("/{id}/historical")
    public ResponseEntity<?> updateSprintHistorical(@PathVariable Long id) {
        sprintService.updateSprintHistorical(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/active")
    public ResponseEntity<?> updateSprintActive(@PathVariable Long id) {
        sprintService.updateSprintActive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    } 

}
