package com.domanski.backend.homePage.service;

import com.domanski.backend.common.model.Product;
import com.domanski.backend.common.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomePageService {

    private final ProductRepository productRepository;

    public List<Product> getSaleProduct() {
        return productRepository.findTop10BySalePriceIsNotNull();
    }
}
