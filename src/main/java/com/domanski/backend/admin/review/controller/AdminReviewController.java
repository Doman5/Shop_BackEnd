package com.domanski.backend.admin.review.controller;

import com.domanski.backend.admin.review.model.AdminReview;
import com.domanski.backend.admin.review.service.AdminReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/reviews")
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    @GetMapping
    public List<AdminReview> getReviews() {
        return adminReviewService.getReviews();
    }

    @PutMapping("{id}/moderate")
    public void moderate(@PathVariable Long id) {
        adminReviewService.moderate(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        adminReviewService.delete(id);
    }
}
