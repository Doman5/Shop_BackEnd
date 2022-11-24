package com.domanski.backend.product.service.dto;

import com.domanski.backend.admin.adminProduct.model.AdminProductCurrency;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private String fullDescription;
    private BigDecimal price;
    private AdminProductCurrency currency;
    private String image;
    private String slug;
    private List<ReviewDto> reviews;
}
