package com.cms.quiz.repository;

import com.cms.quiz.entity.SkipCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkipCountRepository extends JpaRepository<SkipCount,Long> {
    SkipCount findByQuizIdAndUserId(long quizId, String userId);
}
