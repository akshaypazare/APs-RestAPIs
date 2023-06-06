package com.RestAPIs.DTO;

import lombok.Data;

@Data
public class NextQuestionResponse {

    public NextQuestionResponse(String correctAnswer, NextQuestion nextQuestion) {
        this.correctAnswer = correctAnswer;
        this.nextQuestion = nextQuestion;
    }

    private String correctAnswer;
    private NextQuestion nextQuestion;
}
