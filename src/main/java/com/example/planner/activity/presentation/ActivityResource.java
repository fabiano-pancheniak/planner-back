package com.example.planner.activity.presentation;

import com.example.planner.activity.application.ActivityService;
import com.example.planner.activity.application.dto.CreateActivity;
import com.example.planner.activity.domain.Activity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityResource {
    private final ActivityService service;

    public ActivityResource(ActivityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAll(){
        List<Activity> activities = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }

    @GetMapping("/trip/{id}")
    public ResponseEntity<List<Activity>> getByTrip(@PathVariable Integer id){
        List<Activity> activities = service.getByTrip(id);
        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }

    @PostMapping
    public ResponseEntity<Activity> create(@RequestBody CreateActivity payload){
        Activity activity = service.create(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(activity);
    }
}
