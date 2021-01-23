package com.cms.quiz.repository;

import com.cms.quiz.entity.QuizLeaderBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizLeaderBoardRepository extends JpaRepository<QuizLeaderBoard, Long> {
}
