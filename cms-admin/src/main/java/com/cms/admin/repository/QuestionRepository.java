package com.cms.admin.repository;

import com.cms.admin.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions,Long> {
    List<Questions> findByCategoryId(Long categoryId);
}
