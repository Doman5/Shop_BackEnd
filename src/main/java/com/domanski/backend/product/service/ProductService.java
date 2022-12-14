package com.domanski.backend.product.service;

import com.domanski.backend.common.model.Product;
import com.domanski.backend.common.model.Review;
import com.domanski.backend.common.repository.ProductRepository;
import com.domanski.backend.common.repository.ReviewRepository;
import com.domanski.backend.product.service.dto.ProductDto;
import com.domanski.backend.product.service.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ProductDto getProductBySlug(String slug) {
        Product product = productRepository.findBySlug(slug).orElseThrow();
        List<Review> reviews = reviewRepository.findAllByProductIdAndModerated(product.getId(), true);
        return mapToProductDto(product, reviews);
    }

    private ProductDto mapToProductDto(Product product, List<Review> reviews) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .fullDescription(product.getFullDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .currency(product.getCurrency())
                .categoryId(product.getCategoryId())
                .slug(product.getSlug())
                .reviews(reviews.stream().map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .productId(review.getProductId())
                        .authorName(review.getAuthorName())
                        .content(review.getContent())
                        .build()).toList())
                .build();
    }
}
