package com.projects.manager.controllers;

import com.projects.manager.models.DTOs.UserDTO;
import com.projects.manager.models.User;
import com.projects.manager.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<User> add(@RequestBody UserDTO request){
        var user = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
