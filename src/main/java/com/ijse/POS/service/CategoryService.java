package com.ijse.POS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.POS.entity.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    
}
