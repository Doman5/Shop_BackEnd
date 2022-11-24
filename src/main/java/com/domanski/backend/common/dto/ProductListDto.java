package com.domanski.backend.common.dto;

import com.domanski.backend.admin.adminProduct.model.AdminProductCurrency;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder
public class ProductListDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private AdminProductCurrency currency;
    private String image;
    private String slug;
}
