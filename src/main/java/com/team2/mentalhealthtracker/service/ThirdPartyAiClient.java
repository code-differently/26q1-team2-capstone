package com.team2.mentalhealthtracker.service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.team2.mentalhealthtracker.dto.AiRecommendationResponse;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyAiClient {

    private final OpenAIClient client;

    public ThirdPartyAiClient() {
        this.client = OpenAIOkHttpClient.fromEnv();
    }

    public AiRecommendationResponse getRecommendationAndGoal(
            Integer moodScore,
            Integer stressLevel,
            Integer sleepQuality,
            String journalNotes,
            String currentGoal
    ) {
        try {
            String prompt = """
                    You are a supportive assistant inside a mental health tracker app.
                    Based on the user's check-in, provide:
                    1. A short supportive suggestion in 2 to 3 sentences.
                    2. A short practical wellness goal in 1 sentence.

                    Return ONLY in this exact format:
                    SUGGESTION: <text>
                    GOAL: <text>

                    User data:
                    Mood score: %s
                    Stress level: %s
                    Sleep quality: %s
                    Journal notes: %s
                    Current goal: %s
                    """.formatted(
                    safe(moodScore),
                    safe(stressLevel),
                    safe(sleepQuality),
                    safe(journalNotes),
                    safe(currentGoal)
            );

            ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                    .addUserMessage(prompt)
                    .model(ChatModel.GPT_4_1_MINI)
                    .build();

            ChatCompletion completion = client.chat().completions().create(params);

            String output = completion.choices().get(0).message().content().orElse("");

            String suggestion = extractValue(output, "SUGGESTION:");
            String goal = extractValue(output, "GOAL:");

            if (suggestion == null || suggestion.isBlank()) {
                suggestion = "Take a few quiet minutes today to check in with yourself, breathe slowly, and choose one small action that helps you feel steadier.";
            }

            if (goal == null || goal.isBlank()) {
                goal = "Spend 10 minutes today doing one calming activity that supports your mood.";
            }

            return new AiRecommendationResponse(suggestion, goal);

        } catch (Exception e) {
            e.printStackTrace();
            return new AiRecommendationResponse(
                    "Take a few quiet minutes today to check in with yourself, breathe slowly, and choose one small action that helps you feel steadier.",
                    "Spend 10 minutes today doing one calming activity that supports your mood."
            );
        }
    }

    private String extractValue(String text, String label) {
        if (text == null || text.isBlank()) {
            return "";
        }

        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            if (line.trim().startsWith(label)) {
                return line.replace(label, "").trim();
            }
        }

        return "";
    }

    private String safe(Object value) {
        return value == null ? "Not provided" : value.toString();
    }
}