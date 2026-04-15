package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.ResourceResponse;
import com.team2.mentalhealthtracker.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resources")
@CrossOrigin(origins = "http://localhost:5173")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public ResponseEntity<ResourceResponse> getSupportResources() {
        return ResponseEntity.ok(resourceService.getProfessionalResources());
    }
}