package com.domanski.backend.admin.adminProduct.dto;

import com.domanski.backend.admin.adminProduct.model.AdminProductCurrency;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
public class AdminProductDto {

    @NotBlank
    @Length(min = 3)
    private String name;
    @NotBlank
    @Length(min = 3)
    private String category;
    @NotBlank
    @Length(min = 3)
    private String description;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private AdminProductCurrency currency;
}
