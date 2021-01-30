package com.cms.quiz.repository;

import com.cms.quiz.entity.QuizResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResponsesRepository extends JpaRepository<QuizResponses,Long> {
    List<QuizResponses> findByUserIdAndQuizId(String userId, Long quizId);

    @Query(value = "select distinct(question_id) from quiz_responses where quiz_id = ?1", nativeQuery = true)
    List<Long> getBroadcastedDynamicQuizQuestions(Long quizId);

    @Query(value="select count(question_id),question_id,quiz_id from quiz_responses group by question_id, quiz_id having quiz_id= ?1 order by count desc;",nativeQuery = true)
    List<Object[]> getMostAnsweredQuestionId(Long quizId);
}