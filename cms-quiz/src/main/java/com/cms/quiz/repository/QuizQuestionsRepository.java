package com.cms.quiz.repository;

import com.cms.quiz.entity.QuizQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionsRepository extends JpaRepository<QuizQuestions,Long> {
    List<QuizQuestions> findByQuizId(Long quizId);
    List<QuizQuestions> deleteByQuizIdAndQuestionId(Long quizId, Long questionId);

    @Query(value = "select count(*) from quiz_questions where quiz_id = ?1", nativeQuery = true)
    Long countQuestions(Long quizId);
}
