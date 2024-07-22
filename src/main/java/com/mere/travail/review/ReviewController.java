package com.mere.travail.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * @param companyId
     * @return
     */
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    /**
     * @param companyId
     * @param review
     * @return
     */
    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewCreated = reviewService.addReview(companyId, review);
        if (!isReviewCreated)
            return new ResponseEntity<>("Unable to create a review.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Added a new review.", HttpStatus.OK);
    }

    /**
     * @param companyId
     * @param reviewId
     * @return
     */
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
    }

    /**
     * @param CompanyId
     * @param reviewId
     * @param review
     * @return
     */
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable Long companyId,
            @PathVariable Long reviewId,
            @RequestBody Review review) {
        boolean updated = reviewService.updateReview(companyId, reviewId, review);
        if (updated)
            return new ResponseEntity<>("Review updated successfully.", HttpStatus.OK);
        return new ResponseEntity<>("Unable to update review, please try again.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long companyId,
            @PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(companyId, reviewId);
        if (deleted)
            return new ResponseEntity<>("Review deleted successfully.", HttpStatus.OK);
        return new ResponseEntity<>("Unable to delete review, please try again.", HttpStatus.NOT_FOUND);
    }

}
