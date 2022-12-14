package com.domanski.backend.homePage.dto;

import com.domanski.backend.common.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomePageDto {
    private List<Product> saleProducts;
}
