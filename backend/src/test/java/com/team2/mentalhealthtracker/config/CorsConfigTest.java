package com.team2.mentalhealthtracker.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.filter.CorsFilter;

import static org.junit.jupiter.api.Assertions.*;

class CorsConfigTest {

    @Test
    void shouldCreateCorsFilter() {
        CorsConfig config = new CorsConfig();

        CorsFilter filter = config.corsFilter();

        assertNotNull(filter);
        assertTrue(filter instanceof CorsFilter);
    }
}