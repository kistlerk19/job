package com.mere.travail.review;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean addReview(Long companyId, Review review);

    Review getReview(Long companyId, Long reviewId);
}
