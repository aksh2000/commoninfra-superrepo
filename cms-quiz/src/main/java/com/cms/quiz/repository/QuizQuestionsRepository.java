package com.cms.quiz.repository;

import com.cms.quiz.entity.QuizQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionsRepository extends JpaRepository<QuizQuestions,Long> {
}
