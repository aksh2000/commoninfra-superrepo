package com.cms.admin.repository;

import com.cms.admin.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {
    List<Questions> findByCategoryId(Long categoryId);

    List<Questions> findByCategoryIdAndType(long categoryId, int type);
}
