package com.domanski.backend.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private String fullDescription;
    private BigDecimal price;
    private BigDecimal salePrice;
    private String currency;
    private String image;
    private String slug;

    public BigDecimal getEndPrice() {
        return salePrice != null ? salePrice : price;
    }
}
