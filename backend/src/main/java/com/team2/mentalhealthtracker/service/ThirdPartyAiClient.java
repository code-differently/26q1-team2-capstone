package com.team2.mentalhealthtracker.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.team2.mentalhealthtracker.dto.AiRecommendationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ThirdPartyAiClient {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    public ThirdPartyAiClient(
            WebClient.Builder webClientBuilder,
            @Value("${openai.base.url}") String baseUrl
    ) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }

    public AiRecommendationResponse getRecommendationAndGoal(
            Integer moodScore,
            Integer stressLevel,
            Integer sleepQuality,
            String journalNotes,
            String currentGoal
    ) {
        try {

            if (apiKey == null || apiKey.isBlank()) {
                return new AiRecommendationResponse(
                        fallbackSuggestion(moodScore, stressLevel, sleepQuality),
                        fallbackGoal(currentGoal)
                );
            }

            String safeJournalNotes = journalNotes == null ? "" : journalNotes.trim();
            String safeCurrentGoal = currentGoal == null ? "" : currentGoal.trim();

            String prompt = buildPrompt(
                    moodScore,
                    stressLevel,
                    sleepQuality,
                    safeJournalNotes,
                    safeCurrentGoal
            );

            ChatCompletionRequest request = new ChatCompletionRequest(
                    model,
                    List.of(
                            new Message(
                                    "system",
                                    """
                                    You are a supportive wellness assistant for a mental wellness tracking app.
                                    Give gentle, practical, non-diagnostic guidance.
                                    Do not claim to be a therapist or doctor.
                                    Do not provide emergency or crisis instructions unless the user explicitly mentions immediate danger.
                                    
                                    Return your answer in exactly this format:
                                    SUGGESTION: <one short supportive suggestion>
                                    GOAL: <one short realistic goal>
                                    """
                            ),
                            new Message("user", prompt)
                    ),
                    0.7
            );

            ChatCompletionResponse response = webClient.post()
                    .uri("/chat/completions")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(ChatCompletionResponse.class)
                    .block();

            String content = extractAssistantContent(response);

            String suggestion = extractValue(content, "SUGGESTION:");
            String goal = extractValue(content, "GOAL:");

            if (suggestion.isBlank()) {
                suggestion = fallbackSuggestion(moodScore, stressLevel, sleepQuality);
            }
            if (goal.isBlank()) {
                goal = fallbackGoal(currentGoal);
            }

            return new AiRecommendationResponse(suggestion, goal);

        } catch (Exception exception) {
            return new AiRecommendationResponse(
                    fallbackSuggestion(moodScore, stressLevel, sleepQuality),
                    fallbackGoal(currentGoal)
            );
        }
    }

    private String buildPrompt(
            Integer moodScore,
            Integer stressLevel,
            Integer sleepQuality,
            String journalNotes,
            String currentGoal
    ) {
        return """
                Here is the user's latest wellness check-in:
                - Mood score (1-10): %s
                - Stress level (1-10): %s
                - Sleep quality (1-10): %s
                - Journal notes: %s
                - Current goal: %s

                Give:
                1. One short supportive suggestion tailored to today's check-in
                2. One short realistic wellness goal for today or this week

                Keep both concise, practical, and encouraging.
                """.formatted(
                valueOrUnknown(moodScore),
                valueOrUnknown(stressLevel),
                valueOrUnknown(sleepQuality),
                journalNotes.isBlank() ? "No notes provided" : journalNotes,
                currentGoal == null || currentGoal.isBlank() ? "No current goal" : currentGoal
        );
    }

    private String valueOrUnknown(Integer value) {
        return value == null ? "unknown" : String.valueOf(value);
    }

    private String extractAssistantContent(ChatCompletionResponse response) {
        if (response == null
                || response.choices == null
                || response.choices.isEmpty()
                || response.choices.get(0).message == null
                || response.choices.get(0).message.content == null) {
            return "";
        }
        return response.choices.get(0).message.content.trim();
    }

    private String fallbackSuggestion(Integer moodScore, Integer stressLevel, Integer sleepQuality) {
        if (stressLevel != null && stressLevel >= 8) {
            return "Take 5 to 10 quiet minutes today for deep breathing, water, and a short reset.";
        }
        if (moodScore != null && moodScore <= 3) {
            return "Be gentle with yourself today and focus on one small calming activity you can complete.";
        }
        if (sleepQuality != null && sleepQuality <= 3) {
            return "Try a lighter evening routine tonight and aim to reduce stimulation before bed.";
        }
        return "Take a small wellness step today, like a short walk, stretching, or a few quiet minutes to reset.";
    }

    private String fallbackGoal(String currentGoal) {
        if (currentGoal != null && !currentGoal.isBlank()) {
            return currentGoal;
        }
        return "Spend 10 minutes today doing one calming activity for your well-being.";
    }

    private String extractValue(String text, String label) {
        if (text == null || text.isBlank() || label == null || label.isBlank()) {
            return "";
        }

        int start = text.indexOf(label);
        if (start < 0) {
            return "";
        }

        int valueStart = start + label.length();
        String remaining = text.substring(valueStart).trim();

        int nextSuggestion = remaining.indexOf("SUGGESTION:");
        int nextGoal = remaining.indexOf("GOAL:");
        int nextIndex = -1;

        if (nextSuggestion >= 0) {
            nextIndex = nextSuggestion;
        }
        if (nextGoal >= 0 && (nextIndex == -1 || nextGoal < nextIndex)) {
            nextIndex = nextGoal;
        }

        if (nextIndex >= 0) {
            return remaining.substring(0, nextIndex).trim();
        }

        return remaining.trim();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ChatCompletionRequest {
        private final String model;
        private final List<Message> messages;

        @JsonProperty("temperature")
        private final Double temperature;

        public ChatCompletionRequest(String model, List<Message> messages, Double temperature) {
            this.model = model;
            this.messages = messages;
            this.temperature = temperature;
        }

        public String getModel() {
            return model;
        }

        public List<Message> getMessages() {
            return messages;
        }

        public Double getTemperature() {
            return temperature;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Message {
        private final String role;
        private final String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ChatCompletionResponse {
        private List<Choice> choices;

        public List<Choice> getChoices() {
            return choices;
        }

        public void setChoices(List<Choice> choices) {
            this.choices = choices;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Choice {
        private AssistantMessage message;

        public AssistantMessage getMessage() {
            return message;
        }

        public void setMessage(AssistantMessage message) {
            this.message = message;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AssistantMessage {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}