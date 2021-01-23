package com.cms.quiz.repository;

import com.cms.quiz.entity.QuizResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResponsesRepositry extends JpaRepository<QuizResponses,Long> {
    List<QuizResponses> findByUserIdAndQuizId(String userId, Long quizId);
}
