package com.projects.manager.controllers;

import com.projects.manager.models.DTOs.UserRequestDTO;
import com.projects.manager.models.Response;
import com.projects.manager.services.CustomException;
import com.projects.manager.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = {"*"})
public class UsersController {
    @Autowired
    private IUsersService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id) throws CustomException{
        var user = service.findOne(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody UserRequestDTO request, @PathVariable Long id) throws CustomException {
       return ResponseEntity.accepted().body(service.update(request, id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws CustomException{
        var response = service.delete(id);
        if (response) {
            return ResponseEntity.ok(new Response("User deleted successfully.", true));
        }
        return ResponseEntity.notFound().build();
    }
}
