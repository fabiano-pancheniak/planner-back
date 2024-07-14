package com.example.planner.participant.domain;

import com.example.planner.trip.domain.Trip;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "participant")
public class Participant {

    public Participant() {}
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @UuidGenerator
    @Column(name = "public_id", nullable = false)
    private String publicId;

    private String name;
    @Column(nullable = false)
    private String email;

    @Column(name = "confirmed", nullable = false)
    private boolean isConfirmed;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
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

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Trip getTrip() {
        return trip;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", publicId='" + publicId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isConfirmed=" + isConfirmed +
                ", trip=" + trip +
                '}';
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
