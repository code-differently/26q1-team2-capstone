package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.model.ResourceLink;
import com.team2.mentalhealthtracker.repository.ResourceLinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceLinkService {

    private final ResourceLinkRepository resourceLinkRepository;

    public ResourceLinkService(ResourceLinkRepository resourceLinkRepository) {
        this.resourceLinkRepository = resourceLinkRepository;
    }

    public ResourceLink saveResourceLink(ResourceLink resourceLink) {
        return resourceLinkRepository.save(resourceLink);
    }

    public List<ResourceLink> getAllResourceLinks() {
        return resourceLinkRepository.findAll();
    }
}
