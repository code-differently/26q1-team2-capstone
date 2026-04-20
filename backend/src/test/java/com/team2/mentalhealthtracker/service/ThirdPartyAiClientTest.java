package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.dto.AiRecommendationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

class ThirdPartyAiClientTest {

    private ThirdPartyAiClient createClient() {
        return new ThirdPartyAiClient(
                WebClient.builder(),
                "https://api.openai.com/v1"
        );
    }

    @Test
    void shouldReturnFallbackWhenExceptionOccurs() {
        ThirdPartyAiClient client = createClient();

        AiRecommendationResponse response =
                client.getRecommendationAndGoal(null, null, null, null, null);

        assertNotNull(response);
        assertNotNull(response.getSuggestion());
        assertNotNull(response.getSuggestedGoal());
        assertFalse(response.getSuggestion().isBlank());
        assertFalse(response.getSuggestedGoal().isBlank());
    }

    @Test
    void shouldExtractValuesCorrectly() {
        ThirdPartyAiClient client = createClient();

        String mockOutput = """
                SUGGESTION: Take a walk outside
                GOAL: Drink more water
                """;

        String suggestion = invokeExtract(client, mockOutput, "SUGGESTION:");
        String goal = invokeExtract(client, mockOutput, "GOAL:");

        assertEquals("Take a walk outside", suggestion);
        assertEquals("Drink more water", goal);
    }

    private String invokeExtract(ThirdPartyAiClient client, String text, String label) {
        try {
            var method = ThirdPartyAiClient.class
                    .getDeclaredMethod("extractValue", String.class, String.class);
            method.setAccessible(true);
            return (String) method.invoke(client, text, label);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}