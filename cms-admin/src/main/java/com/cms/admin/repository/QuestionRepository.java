package com.cms.admin.repository;

import com.cms.admin.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions,Long> {
    void deleteByQuizIdAndQuestionId(Long quizId, Long questionId);
}
