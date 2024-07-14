package com.example.planner.link.presentation;

import com.example.planner.link.application.LinkService;
import com.example.planner.link.application.dto.LinkRequest;
import com.example.planner.link.domain.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/link")
public class LinkResource {
    private final LinkService service;

    public LinkResource(LinkService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Link>> get(){
        List<Link> links = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(links);
    }

    @GetMapping("/trip/{id}")
    public ResponseEntity<List<Link>> getByTrip(@PathVariable Integer id){
        List<Link> links = service.getByTrip(id);
        return ResponseEntity.status(HttpStatus.OK).body(links);
    }

    @PostMapping
    public ResponseEntity<Link> create(@RequestBody LinkRequest payload){
        Link link = service.create(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(link);
    }
}
