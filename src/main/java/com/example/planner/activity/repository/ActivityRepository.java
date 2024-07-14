package com.example.planner.activity.repository;

import com.example.planner.activity.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByTripId(Integer id);
}
