package com.domanski.backend.admin.product.dto;

import com.domanski.backend.admin.product.model.AdminProductCurrency;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
public class AdminProductDto {

    @NotBlank
    @Length(min = 4)
    private String name;
    @NotNull
    private Long categoryId;
    @NotBlank
    @Length(min = 4)
    private String description;
    @NotBlank
    @Length(min = 4)
    private String fullDescription;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private AdminProductCurrency currency;
    private String image;
    @NotBlank
    @Length(min = 4)
    private String slug;
}
