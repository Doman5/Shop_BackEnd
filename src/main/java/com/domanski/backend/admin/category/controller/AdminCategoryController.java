package com.domanski.backend.admin.category.controller;

import com.domanski.backend.admin.category.dto.AdminCategoryDto;
import com.domanski.backend.admin.category.model.AdminCategory;
import com.domanski.backend.admin.category.servise.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public List<AdminCategory> getCategories() {
        return adminCategoryService.getAllCategories();
    }

    @GetMapping("{id}")
    public AdminCategory getCategory(@PathVariable Long id) {
        return adminCategoryService.getCategoryById(id);
    }

    @PostMapping
    public AdminCategory createCategory(@RequestBody AdminCategoryDto adminCategoryDto) {
        return adminCategoryService.createCategory(adminCategoryDto);
    }

    @PutMapping("{id}")
    public AdminCategory editCategory(@PathVariable Long id, @RequestBody AdminCategoryDto adminCategoryDto) {
        return adminCategoryService.editCategory(id, adminCategoryDto);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id) {
        adminCategoryService.deleteCategory(id);
    }
}
