package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.ResourceResponse;
import com.team2.mentalhealthtracker.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResourceController.class)
class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ResourceService resourceService;

    @Test
    void shouldReturnProfessionalResources() throws Exception {
        ResourceResponse response = new ResourceResponse(
                "Additional support resources are available below.",
                List.of(
                        "https://988lifeline.org",
                        "https://www.nami.org/support-education",
                        "https://www.mentalhealth.gov/get-help"
                )
        );

        when(resourceService.getProfessionalResources()).thenReturn(response);

        mockMvc.perform(get("/api/resources"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Additional support resources are available below."))
                .andExpect(jsonPath("$.resourceLinks[0]").value("https://988lifeline.org"))
                .andExpect(jsonPath("$.resourceLinks[1]").value("https://www.nami.org/support-education"))
                .andExpect(jsonPath("$.resourceLinks[2]").value("https://www.mentalhealth.gov/get-help"));
    }
}