package com.domanski.backend.category.controller;

import com.domanski.backend.category.Dto.CategoryProductsDto;
import com.domanski.backend.category.model.Category;
import com.domanski.backend.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }


    @GetMapping("categories/{slug}/products")
    public CategoryProductsDto getCategoryWithProducts(@PathVariable
                                                @Pattern(regexp = "[a-z0-9\\-]+")
                                                @Length(max = 255)
                                                String slug,
                                                       Pageable pageable) {
        return categoryService.getCategoryWithProducts(slug, pageable);
    }
}
