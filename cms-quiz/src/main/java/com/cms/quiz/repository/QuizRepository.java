package com.cms.quiz.repository;

import com.cms.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    List<Quiz> findByAdminId(String adminId);

    @Query(value = "select * from quiz where type = ?2 and end_time > ?1",nativeQuery = true )
    List<Quiz> getStaticQuiz(Date d ,int type);

//    @Query(value = "select * from quiz where type = ?2 and end_time > ?1",nativeQuery = true )
@Query(value = "select * from quiz where type = ?2 and start_time < ?1  or end_time > ?1",nativeQuery = true )
List<Quiz> getDynamicQuiz(Date d, int type);
}
