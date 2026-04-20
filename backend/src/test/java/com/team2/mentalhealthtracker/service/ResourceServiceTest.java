package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.dto.ResourceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceServiceTest {

    private final ResourceService resourceService = new ResourceService();

    @Test
    void shouldReturnProfessionalResources() {
        ResourceResponse response = resourceService.getProfessionalResources();

        assertNotNull(response);
        assertEquals("Additional support resources are available below.", response.getMessage());

        assertNotNull(response.getResourceLinks());
        assertEquals(3, response.getResourceLinks().size());

        assertTrue(response.getResourceLinks().contains("https://988lifeline.org"));
        assertTrue(response.getResourceLinks().contains("https://www.nami.org/support-education"));
        assertTrue(response.getResourceLinks().contains("https://www.mentalhealth.gov/get-help"));
    }
}