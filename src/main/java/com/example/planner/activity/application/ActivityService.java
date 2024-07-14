package com.example.planner.activity.application;

import com.example.planner.activity.application.dto.CreateActivity;
import com.example.planner.activity.domain.Activity;
import com.example.planner.activity.repository.ActivityRepository;
import com.example.planner.exceptions.TripNotFoundException;
import com.example.planner.trip.application.TripService;
import com.example.planner.trip.domain.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository repository;
    private final TripService tripService;

    public ActivityService(ActivityRepository repository, TripService tripService) {
        this.repository = repository;
        this.tripService = tripService;
    }

    public List<Activity> getAll(){
        return repository.findAll();
    }

    public List<Activity> getByTrip(Integer id){
        return repository.findByTripId(id);
    }

    public Activity create(CreateActivity payload){
        Trip trip = tripService.getByPublicId(payload.tripId()).orElseThrow(() -> new TripNotFoundException(payload.tripId()));

        Activity activity = new Activity();
        activity.setDescription(payload.description());
        activity.setDateTime(payload.dateTime());
        activity.setTrip(trip);

        repository.save(activity);

        return activity;
    }
}
