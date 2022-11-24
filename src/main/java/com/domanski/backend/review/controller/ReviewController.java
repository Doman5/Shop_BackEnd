package com.domanski.backend.review.controller;

import com.domanski.backend.common.model.Review;
import com.domanski.backend.review.dto.ReviewDto;
import com.domanski.backend.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Review addReview(@RequestBody @Valid ReviewDto reviewDto) {
        return reviewService.addReview(reviewDto);
    }
}
