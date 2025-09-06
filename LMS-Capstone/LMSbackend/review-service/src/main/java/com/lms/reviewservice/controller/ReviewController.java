package com.lms.reviewservice.controller;

import com.lms.reviewservice.model.Review;
import com.lms.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService review_service;

    @PostMapping("/add")
    public ResponseEntity<Review> add_review(@RequestBody Review review) {
        return ResponseEntity.status(201).body(review_service.add_review(review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update_review(@PathVariable String id, @RequestBody Review review) {
        return ResponseEntity.ok(review_service.update_review(id, review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteReview(@PathVariable("id") String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Review id required"));
            }

            boolean deleted = review_service.softDeleteById(id.trim());
            if (!deleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Review not found or already deleted"));
            }

            return ResponseEntity.ok(Map.of("message", "Review deleted successfully"));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Server error", "details", ex.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable("id") String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Review id required"));
            }

            Optional<Review> opt = review_service.findById(id.trim());
            if (opt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Review not found"));
            }

            return ResponseEntity.ok(opt.get());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Server error", "details", ex.getMessage()));
        }
    }


    @GetMapping("/catalogue/{catalogue_id}")
    public ResponseEntity<List<Review>> get_reviews_by_catalogue(@PathVariable String catalogue_id) {
        return ResponseEntity.ok(review_service.get_reviews_by_catalogue(catalogue_id));
    }

    @GetMapping("/member/{member_id}")
    public ResponseEntity<List<Review>> get_reviews_by_member(@PathVariable String member_id) {
        return ResponseEntity.ok(review_service.get_reviews_by_member(member_id));
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        try {
            List<Review> reviews = review_service.findAll(); // fetch all reviews
            return ResponseEntity.ok(reviews);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Server error", "details", ex.getMessage()));
        }
    }
}
