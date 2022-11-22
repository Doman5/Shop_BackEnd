package com.domanski.backend.category.service;

import com.domanski.backend.category.Dto.CategoryProductsDto;
import com.domanski.backend.category.model.Category;
import com.domanski.backend.category.repository.CategoryRepository;
import com.domanski.backend.product.model.Product;
import com.domanski.backend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryProductsDto getCategoryWithProducts(String slug, Pageable pageable) {
        Category category = categoryRepository.findBySlug(slug).orElseThrow();
        Page<Product> page = productRepository.findByCategoryId(category.getId(), pageable);
        return new CategoryProductsDto(category, page);
    }
}
