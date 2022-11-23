package com.domanski.backend.category.Dto;

import com.domanski.backend.category.model.Category;
import com.domanski.backend.product.model.dto.ProductListDto;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}
