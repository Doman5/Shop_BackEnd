package com.domanski.backend.admin.adminProduct.controller;

import com.domanski.backend.admin.adminProduct.model.AdminProduct;
import com.domanski.backend.admin.adminProduct.service.AdminProductService;
import com.domanski.backend.product.repository.AdminProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    @GetMapping("admin/products")
    public Page<AdminProduct> getProducts(Pageable pageable) {
        return adminProductService.getProducts(pageable);
    }
}
