package com.example.planner.link.application;

import com.example.planner.exceptions.TripNotFoundException;
import com.example.planner.link.application.dto.LinkRequest;
import com.example.planner.link.domain.Link;
import com.example.planner.link.repository.LinkRepository;
import com.example.planner.trip.application.TripService;
import com.example.planner.trip.domain.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {
    private final LinkRepository repository;
    private final TripService tripService;

    LinkService(LinkRepository repository, TripService tripService){
        this.repository = repository;
        this.tripService = tripService;
    }

    public List<Link> getAll(){return repository.findAll();}
    public List<Link> getByTrip(Integer id){return repository.findByTripId(id);}

    public Link create(LinkRequest payload){
        Trip trip = tripService.getById(payload.tripId()).orElseThrow(() -> new TripNotFoundException(payload.tripId().toString()));
        Link link = new Link();
        link.setDescription(payload.description());
        link.setUrl(payload.link());
        link.setTrip(trip);

        repository.save(link);

        return link;
    }
}
