package com.example.planner.trip.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "participant")
public class Participant {

    public Participant() {}
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    @Column(nullable = false)
    private String email;
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
