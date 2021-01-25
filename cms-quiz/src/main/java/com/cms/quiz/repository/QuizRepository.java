package com.cms.quiz.repository;

import com.cms.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    List<Quiz> findByAdminId(String adminId);
    @Query("select * from quiz q where q.type=0 and q.end_time>?1" )
    List<Quiz> getStaticQuiz(Date d);
}
