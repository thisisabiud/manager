/**
 * @author Abiud T Samo
 */
package com.projects.manager.controllers;

import com.projects.manager.models.DTOs.ProjectRequestDTO;
import com.projects.manager.models.Response;
import com.projects.manager.services.CustomException;
import com.projects.manager.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/projects")
@CrossOrigin(origins = {"*"})
public class ProjectController {
    @Autowired
    private ProjectsService service;

    @GetMapping
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws CustomException {
        return ResponseEntity.ok(service.findOne(id));
    }

    @GetMapping("{id}/team")
    public ResponseEntity<?> projectTeam(@PathVariable long id) throws CustomException {
        return ResponseEntity.ok(service.findProjectTeam(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectRequestDTO request){
        service.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("Project created successfully.",true));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody ProjectRequestDTO request, @PathVariable Long id) throws CustomException{
        service.update(request, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Project updated successfully.",true));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws CustomException{
        var response = service.delete(id);
        if (response) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("team/{id}")
    public ResponseEntity<?> removeMember(@PathVariable Long id) throws CustomException{
        boolean response =  service.removeMember(id);
        if (!response) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
