package com.domanski.backend.admin.adminProduct.controller;

import com.domanski.backend.admin.adminProduct.dto.AdminProductDto;
import com.domanski.backend.admin.adminProduct.model.AdminProduct;
import com.domanski.backend.admin.adminProduct.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

    public static final Long EMPTY_ID = null;
    private final AdminProductService adminProductService;

    @GetMapping("admin/products")
    public Page<AdminProduct> getProducts(Pageable pageable) {
        return adminProductService.getProducts(pageable);
    }

    @GetMapping("admin/products/{id}")
    public AdminProduct getProduct(@PathVariable Long id) {
        return adminProductService.getProduct(id);
    }

    @PostMapping("admin/products")
    public AdminProduct createProduct(@RequestBody @Valid AdminProductDto adminProductDto) {
        return adminProductService.createProduct(mapProduct(adminProductDto, EMPTY_ID));
    }

    @PutMapping("admin/products/{id}")
    public AdminProduct updateProduct(@RequestBody @Valid AdminProductDto adminProductDto, @PathVariable Long id) {
        return adminProductService.updateProduct(mapProduct(adminProductDto,id));
    }

    private static AdminProduct mapProduct(AdminProductDto adminProductDto, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProductDto.getName())
                .description(adminProductDto.getDescription())
                .category(adminProductDto.getCategory())
                .price(adminProductDto.getPrice())
                .currency(adminProductDto.getCurrency())
                .build();
    }
}
