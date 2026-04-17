package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.dto.ResourceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    public ResourceResponse getProfessionalResources(){
        return new ResourceResponse(
                "Additional support resources are available below.",
                List.of(
                        "https://988lifeline.org",
                        "https://www.nami.org/support-education",
                        "https://www.mentalhealth.gov/get-help"
                )
        );
    }
}