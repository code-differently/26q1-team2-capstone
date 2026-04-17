package com.team2.mentalhealthtracker.dto;

import java.util.List;

public class ResourceResponse {

    private String message;
    private List<String> resourceLinks;

    public ResourceResponse() {
    }

    public ResourceResponse(String message, List<String> resourceLinks) {
        this.message = message;
        this.resourceLinks = resourceLinks;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getResourceLinks() {
        return resourceLinks;
    }
}