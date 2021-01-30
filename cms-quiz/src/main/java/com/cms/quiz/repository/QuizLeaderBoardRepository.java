package com.cms.quiz.repository;

import com.cms.quiz.dto.LeaderBoardList;
import com.cms.quiz.entity.QuizLeaderBoard;
import com.cms.quiz.pojo.MainLeaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QuizLeaderBoardRepository extends JpaRepository<QuizLeaderBoard, Long> {
    List<QuizLeaderBoard> findByQuizIdOrderByTotalScoreDesc(Long quizId);
    QuizLeaderBoard findByQuizIdAndUserId(Long quizId, String userId);

    @Transactional
    @Modifying
    @Query(value = "update leaderboard set total_score=total_score+?3 where quiz_id = ?1 and user_id = ?2 ",nativeQuery = true)
    int updateLeaderBoard(Long quizId, String userId, double score);

    @Query(value = "select user_id, sum(total_score) from leaderboard group by user_id order by sum desc", nativeQuery = true)
    List<Object[]> getMainLeaderBoard();

//    @Query(value = "select * from leaderboard")
//    LeaderBoardList getUserRating(String userId);
}

