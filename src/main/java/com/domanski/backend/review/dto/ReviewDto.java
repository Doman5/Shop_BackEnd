package com.domanski.backend.review.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class ReviewDto {
    private Long productId;
    @Length(min = 2, max = 60)
    private String authorName;
    @Length(min = 2, max = 400)
    private String content;
}
