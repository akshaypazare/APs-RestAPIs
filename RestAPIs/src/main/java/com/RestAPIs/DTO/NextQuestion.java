package com.RestAPIs.DTO;

import lombok.Data;

@Data
public class NextQuestion {

    public NextQuestion(Long question_Id, String question) {
        this.question_Id = question_Id;
        this.question = question;
    }

    private Long question_Id;
    private String question;
}
