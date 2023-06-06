package com.RestAPIs.Repository;

import com.RestAPIs.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {


    @Query("SELECT q FROM Question q WHERE q.id > :currentQuestionId ORDER BY q.id ASC")
    List<Question> findNextQuestions(Long currentQuestionId);
}
