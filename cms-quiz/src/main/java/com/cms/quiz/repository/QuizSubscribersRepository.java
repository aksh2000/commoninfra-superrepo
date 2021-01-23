package com.cms.quiz.repository;

import com.cms.quiz.entity.QuizSubscribers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizSubscribersRepository extends JpaRepository<QuizSubscribers,Long> {
}
