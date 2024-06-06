package com.projects.manager.controllers;

import com.projects.manager.models.DTOs.UserDTO;
import com.projects.manager.models.User;
import com.projects.manager.services.CustomException;
import com.projects.manager.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UsersController {
    @Autowired
    private IUsersService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        var users = service.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable UUID id) throws CustomException{
        var user = service.findOne(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody UserDTO request, @PathVariable UUID id) throws CustomException {
       var user =  service.update(request, id);
       return ResponseEntity.accepted().body(user);
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody UserDTO request){
        var user = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) throws CustomException{
        var response = service.delete(id);
        if (response) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
