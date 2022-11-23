package com.domanski.backend.review.controller;

import com.domanski.backend.review.model.Review;
import com.domanski.backend.review.model.dto.ReviewDto;
import com.domanski.backend.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
