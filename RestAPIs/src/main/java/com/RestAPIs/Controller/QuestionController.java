package com.RestAPIs.Controller;

import com.RestAPIs.Entity.Question;
import com.RestAPIs.Repository.QuestionRepository;
import com.RestAPIs.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/RestAPIs")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    //localhost:8080/RestAPIs/getQuestion
    @GetMapping("/getQuestion")
    public ResponseEntity<Object> saveQuestionData(){
        return new ResponseEntity<>(questionService.saveQuestionData(), HttpStatus.CREATED);
    }


    //localhost:8080/RestAPIs/play
    @GetMapping("/play")
    public ResponseEntity<Map<String, Object>> getQuestion() {
        return new ResponseEntity<>(questionService.getQuestionAndQuestionId(), HttpStatus.OK);
    }



    //localhost:8080/RestAPIs/next
    //and provide the required payload which is present in the database(fetch by the first api)
         // to find the correctAnswer and nextQuestion
    @PostMapping("/next")
    public ResponseEntity<Object> getAnswerAndNextQuestion(@RequestBody Map<String, Object> answerAndQuestionId){
        return new ResponseEntity<>(questionService.getCorrectAnswerAndNextQuestion(answerAndQuestionId), HttpStatus.OK);
    }






}
