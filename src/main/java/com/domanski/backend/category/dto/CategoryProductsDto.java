package com.domanski.backend.category.dto;

import com.domanski.backend.common.model.Category;
import com.domanski.backend.common.dto.ProductListDto;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}
