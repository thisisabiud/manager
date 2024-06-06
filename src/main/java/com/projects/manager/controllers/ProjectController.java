package com.projects.manager.controllers;

import com.projects.manager.models.DTOs.ProjectDTO;
import com.projects.manager.services.CustomException;
import com.projects.manager.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    @Autowired
    private ProjectsService service;

    @GetMapping
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable UUID id) throws CustomException {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectDTO request){
        var project = service.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(project);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody ProjectDTO request, @PathVariable UUID id) throws CustomException{
        var response = service.update(request, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) throws CustomException{
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
