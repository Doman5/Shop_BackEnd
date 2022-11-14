package com.domanski.backend.product.controller;
import com.domanski.backend.product.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return List.of(
                new Product("produkt 1","kategoria 1", "opis 1", new BigDecimal("8.99"), "PLN"),
                new Product("produkt 2","kategoria 2", "opis 2", new BigDecimal("8.99"), "PLN"),
                new Product("produkt 3","kategoria 3", "opis 3", new BigDecimal("8.99"), "PLN"),
                new Product("produkt 4","kategoria 4", "opis 4", new BigDecimal("8.99"), "PLN")
                );
    }
}
