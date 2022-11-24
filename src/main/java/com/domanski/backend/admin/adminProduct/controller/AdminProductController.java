package com.domanski.backend.admin.adminProduct.controller;

import com.domanski.backend.admin.adminProduct.dto.AdminProductDto;
import com.domanski.backend.admin.adminProduct.dto.UploadResponse;
import com.domanski.backend.admin.adminProduct.model.AdminProduct;
import com.domanski.backend.admin.adminProduct.service.AdminProductImageService;
import com.domanski.backend.admin.adminProduct.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.domanski.backend.admin.common.utils.SlugifyUtils.slugifySlug;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

    public static final Long EMPTY_ID = null;
    private final AdminProductService adminProductService;
    private final AdminProductImageService adminProductImageService;

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

    @DeleteMapping("admin/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        adminProductService.deleteProduct(id);
    }

    @PostMapping("admin/products/upload-image")
    public UploadResponse uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        try(InputStream inputStream = multipartFile.getInputStream()) {
            String savedFilename = adminProductImageService.uploadImage(multipartFile.getOriginalFilename(), inputStream);
            return new UploadResponse(savedFilename);
        } catch (IOException e) {
            throw new RuntimeException("błąd zapisu" + e);
        }
    }

    @GetMapping("/data/productImage/{filename}")
    public ResponseEntity<Resource> serveFiles(@PathVariable String filename) throws IOException {
        Resource file = adminProductImageService.serveFiles(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(filename)))
                .body(file);
    }

    private static AdminProduct mapProduct(AdminProductDto adminProductDto, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProductDto.getName())
                .description(adminProductDto.getDescription())
                .fullDescription(adminProductDto.getFullDescription())
                .categoryId(adminProductDto.getCategoryId())
                .price(adminProductDto.getPrice())
                .currency(adminProductDto.getCurrency())
                .image(adminProductDto.getImage())
                .slug(slugifySlug(adminProductDto.getSlug()))
                .build();
    }
}
