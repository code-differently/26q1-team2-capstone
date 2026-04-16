package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.model.ResourceLink;
import com.team2.mentalhealthtracker.service.ResourceLinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "http://localhost:3000")
public class ResourceController {

    private final ResourceLinkService resourceLinkService;

    public ResourceController(ResourceLinkService resourceLinkService) {
        this.resourceLinkService = resourceLinkService;
    }

    @PostMapping
    public ResourceLink createResource(@RequestBody ResourceLink resourceLink) {
        return resourceLinkService.saveResourceLink(resourceLink);
    }

    @GetMapping
    public List<ResourceLink> getAllResources() {
        return resourceLinkService.getAllResourceLinks();
    }
}
