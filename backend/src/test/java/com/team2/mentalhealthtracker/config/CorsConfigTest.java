package com.team2.mentalhealthtracker.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.*;

class CorsConfigTest {

    @Test
    void shouldCreateCorsConfigurer() {
        CorsConfig config = new CorsConfig();

        WebMvcConfigurer configurer = config.corsConfigurer();

        assertNotNull(configurer);
    }
}