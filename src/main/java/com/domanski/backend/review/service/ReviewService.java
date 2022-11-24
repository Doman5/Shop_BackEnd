package com.domanski.backend.review.service;

import com.domanski.backend.common.model.Review;
import com.domanski.backend.review.dto.ReviewDto;
import com.domanski.backend.review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(ReviewDto review) {
        return reviewRepository.save(mapReview(review));
    }

    private Review mapReview(ReviewDto reviewDto) {
        return Review.builder()
                .authorName(cleanContent(reviewDto.getAuthorName()))
                .productId(reviewDto.getProductId())
                .content(cleanContent(reviewDto.getContent()))
                .build();
    }

    private String cleanContent(String string) {
        return Jsoup.clean(string, Safelist.none());
    }
}
