package com.cms.admin.repository;

import com.cms.admin.entity.NonScreenedQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NonScreenedQuestionsRepository extends JpaRepository<NonScreenedQuestions, Long> {
    Optional<NonScreenedQuestions> findFirstByOrderByTypeAsc();
}
