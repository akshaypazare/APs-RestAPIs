package com.RestAPIs.Service;

import com.RestAPIs.DTO.QuestionDto;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    List<QuestionDto> saveQuestionData();

    Map<String, Object> getQuestionAndQuestionId();

    Object getCorrectAnswerAndNextQuestion(Map<String, Object> answerAndQuestionId);
}
