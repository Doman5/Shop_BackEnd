package com.domanski.backend.category.service;

import com.domanski.backend.category.dto.CategoryProductsDto;
import com.domanski.backend.common.model.Category;
import com.domanski.backend.category.repository.CategoryRepository;
import com.domanski.backend.common.model.Product;
import com.domanski.backend.common.dto.ProductListDto;
import com.domanski.backend.common.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        Page<Product> products = productRepository.findByCategoryId(category.getId(), pageable);
        List<ProductListDto> productListDtos = products.getContent().stream()
                .map(product -> ProductListDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .currency(product.getCurrency())
                        .slug(product.getSlug())
                        .build())
                .toList();
        return new CategoryProductsDto(category, new PageImpl<>(productListDtos, pageable, products.getTotalElements()) {
        });
    }
}
