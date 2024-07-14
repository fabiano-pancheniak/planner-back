package com.example.planner.link.repository;

import com.example.planner.link.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Integer> {
    List<Link> findByTripId(Integer id);
}
