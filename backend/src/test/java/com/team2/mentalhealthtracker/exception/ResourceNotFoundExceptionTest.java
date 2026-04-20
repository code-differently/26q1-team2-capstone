package com.team2.mentalhealthtracker.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void shouldCreateResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");

        assertNotNull(exception);
        assertEquals("Resource not found", exception.getMessage());
    }
}