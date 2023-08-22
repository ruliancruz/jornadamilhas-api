package com.jornadamilhas.api.services;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class OpenAI
{
    //Place your OpenAI API KEY here
    private static final String OPENAI_API_KEY = "KEY";

    public static String generateAIText(String prompt)
    {
        OpenAiService service = new OpenAiService(OPENAI_API_KEY);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model("text-davinci-003")
                .maxTokens(1000)
                .build();

        return service.createCompletion(completionRequest).getChoices().get(0).getText().replace("\n", "");
    }
}