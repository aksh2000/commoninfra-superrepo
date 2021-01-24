package com.cms.quiz.service;

import com.cms.quiz.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Category addCategory(Category category);

    Optional<Category> getCategoryDetails(Long categoryId);

    List<Category> findAll();
}
