package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.ResourceResponse;
import com.team2.mentalhealthtracker.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService){
        this.resourceService = resourceService;
    }

    @GetMapping
    public ResponseEntity<ResourceResponse> getResources(){
        return ResponseEntity.ok(
                resourceService.getProfessionalResources());
    }
}