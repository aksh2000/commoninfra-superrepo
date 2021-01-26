package com.cms.quiz.repository;

import com.cms.quiz.entity.QuizLeaderBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QuizLeaderBoardRepository extends JpaRepository<QuizLeaderBoard, Long> {
    List<QuizLeaderBoard> findByQuizId(Long quizId);
    QuizLeaderBoard findByQuizIdAndUserId(Long quizId, String userId);

    @Transactional
    @Modifying
    @Query(value = "update leaderboard set total_score=total_score+?3 where quiz_id = ?1 and user_id = ?2 ",nativeQuery = true)
    int updateLeaderBoard(Long quizId, String userId, double score);
}

