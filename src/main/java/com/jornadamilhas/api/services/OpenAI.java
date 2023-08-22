package com.jornadamilhas.api.services;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;

public class OpenAI
{
    @Value("${api.services.openai.apikey}")
    private static String apiKey;
    
    public static String generateAIText(String prompt)
    {
        OpenAiService service = new OpenAiService(apiKey);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model("text-davinci-003")
                .maxTokens(1000)
                .build();

        return service.createCompletion(completionRequest).getChoices().get(0).getText().replace("\n", "");
    }
}