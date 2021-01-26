package com.cms.admin.repository;

import com.cms.admin.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {
    @Query(value = "select * from questions", nativeQuery = true)
     List<Questions> getAllQuestions();


    List<Questions> findByCategoryId(Long categoryId);

    List<Questions> findByCategoryIdAndType(long categoryId, int type);

    List<Questions> findByType(int type);
}
