package com.ijse.POS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.POS.entity.Category;
import com.ijse.POS.service.CategoryService;

@RestController
@CrossOrigin(origins="*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>>getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category>createCategory(@RequestBody Category category){
        Category createdCategory=categoryService.createCategory(category);
        return ResponseEntity.status(201).body(createdCategory);
        
    }
    
}
