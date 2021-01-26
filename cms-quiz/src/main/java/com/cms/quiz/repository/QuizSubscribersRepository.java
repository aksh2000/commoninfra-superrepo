package com.cms.quiz.repository;

import com.cms.quiz.entity.Quiz;
import com.cms.quiz.entity.QuizSubscribers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface QuizSubscribersRepository extends JpaRepository<QuizSubscribers,Long> {
    List<QuizSubscribers> findByQuizId(Long quizId);

    List<Quiz> findByUserId(String userId);

    QuizSubscribers findByQuizIdAndUserId(Long quizId, String userId);

    @Transactional
    @Modifying
    @Query(value = "update subscribers  set start_time = ?1 where user_id = ?2 and quiz_id = ?3",nativeQuery = true)
    int upDateStartTime(Date d, String userId, Long quizId);

    @Transactional
    @Modifying
    @Query(value = "update subscribers  set end_time = ?1 where user_id = ?2 and quiz_id = ?3",nativeQuery = true)
    int upDateEndTime(Date d,String userId, Long quizId);
}
