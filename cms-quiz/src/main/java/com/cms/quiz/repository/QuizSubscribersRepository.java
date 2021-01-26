package com.cms.quiz.repository;

import com.cms.quiz.entity.Quiz;
import com.cms.quiz.entity.QuizSubscribers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizSubscribersRepository extends JpaRepository<QuizSubscribers,Long> {
    List<QuizSubscribers> findByQuizId(Long quizId);
    List<Quiz> findByUserId(String userId);

    QuizSubscribers findByQuizIdAndUserId(Long quizId, String userId);
}
