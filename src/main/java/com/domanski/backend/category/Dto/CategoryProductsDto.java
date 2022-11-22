package com.domanski.backend.category.Dto;

import com.domanski.backend.category.model.Category;
import com.domanski.backend.product.model.Product;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<Product> products) {
}
