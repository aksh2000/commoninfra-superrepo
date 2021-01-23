package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.Category;
import com.cms.quiz.repository.CategoryRepository;
import com.cms.quiz.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategoryDetails(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
