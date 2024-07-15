package com.mere.travail.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mere.travail.company.Company;
import com.mere.travail.company.CompanyService;
import com.mere.travail.review.Review;
import com.mere.travail.review.ReviewRepository;
import com.mere.travail.review.ReviewService;

@Service
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompany(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> review.getId()
                        .equals(reviewId))
                .findFirst()
                .orElse(null);
    }
}
