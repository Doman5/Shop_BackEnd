package com.domanski.backend.product.controller;
import com.domanski.backend.product.model.Product;
import com.domanski.backend.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
