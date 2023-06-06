package com.RestAPIs.Service.Impl;

import com.RestAPIs.DTO.NextQuestion;
import com.RestAPIs.DTO.NextQuestionResponse;
import com.RestAPIs.DTO.QuestionDto;
import com.RestAPIs.Entity.Category;
import com.RestAPIs.Entity.Question;
import com.RestAPIs.Repository.CategoryRepository;
import com.RestAPIs.Repository.QuestionRepository;
import com.RestAPIs.Service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final WebClient webClient;

    private final ModelMapper mapper;

    private final CategoryRepository categoryRepository;

    private final QuestionRepository questionRepository;

    AtomicInteger currentIndex = new AtomicInteger(0);

    public QuestionServiceImpl(WebClient webClient, ModelMapper mapper, CategoryRepository categoryRepository, QuestionRepository questionRepository) {
        this.webClient = webClient;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;

    }


    @Override
    public List<QuestionDto> saveQuestionData() {


        String questionData = webClient
                .get()
                .uri("https://jservice.io/api/random")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();

        List<QuestionDto> questionDtos = null;

        try {
            questionDtos = objectMapper
                    .readValue(questionData, objectMapper.getTypeFactory()
                            .constructCollectionType(List.class, QuestionDto.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<Question> collect = questionDtos.stream().map(questionDto -> mapper.map(questionDto, Question.class)).collect(Collectors.toList());

        List<Question> questions = collect.stream().map(question -> {

            Category category = question.getCategory();
            Category savedCategory = categoryRepository.save(category);

            question.setCategory(savedCategory);

            return questionRepository.save(question);
        }).collect(Collectors.toList());

        List<QuestionDto> questionDto = questions.stream().map(question -> mapper.map(question, QuestionDto.class)).collect(Collectors.toList());


        return questionDto;
    }


    @Override
    public Map<String, Object> getQuestionAndQuestionId() {

        List<Question> questionList = questionRepository.findAll();

        int index = currentIndex.getAndIncrement();
        int effectiveIndex = index % questionList.size();
        Question question = questionList.get(effectiveIndex);

        Map<String, Object> questionIdQuestion = new HashMap<>();

        questionIdQuestion.put("id",question.getId());
        questionIdQuestion.put("question", question.getQuestion());

        return questionIdQuestion;

    }

    @Override
    public Object getCorrectAnswerAndNextQuestion(Map<String, Object> answerAndQuestionId) {

        Integer question_id = (Integer) answerAndQuestionId.get("question_id");
        Object answer = answerAndQuestionId.get("answer");

        Question question = questionRepository.findById(Long.valueOf(question_id)).get();
        String correctAnswer = question.getAnswer();

        List<Question> listOfQuestions = questionRepository.findNextQuestions(Long.valueOf(question_id));

        if (listOfQuestions.size() > 0) {
            Question nextQuestion = listOfQuestions.get(0);
            Long nextQuestionId = nextQuestion.getId();

            String nextQuestionQ = nextQuestion.getQuestion();

            NextQuestionResponse nextQuestionResponse = new NextQuestionResponse(correctAnswer, new NextQuestion(nextQuestionId, nextQuestionQ));

            return nextQuestionResponse;

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }


    }
}
