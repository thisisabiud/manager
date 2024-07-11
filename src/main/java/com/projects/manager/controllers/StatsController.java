package com.projects.manager.controllers;


import com.projects.manager.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stats")
@CrossOrigin(origins = {"*"})
public class StatsController {
    @Autowired
    private StatsService service;

    @GetMapping
    public ResponseEntity<?> getStats(){
        return ResponseEntity.ok(service.general());
    }
}
