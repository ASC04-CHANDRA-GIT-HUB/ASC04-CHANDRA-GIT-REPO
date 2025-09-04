package com.lms.reviewservice.service;

import com.lms.reviewservice.model.Review;
import com.lms.reviewservice.repository.ReviewRepository;
import com.lms.common.util.IdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository review_repository;

    @Autowired
    private RestTemplate rest_template;

    private static final String CIRCULATION_SERVICE_URL =
            "http://localhost:8085/circulations/exists?member_id={member_id}&catalogue_id={catalogue_id}";

    private static final String CATALOGUE_SERVICE_URL =
            "http://localhost:8083/catalogues/{catalogue_id}/rating";

    public Review add_review(Review review) {
        // ✅ Prepare URI variables
        Map<String, String> uriVars = Map.of(
                "member_id", review.getMember_id(),
                "catalogue_id", review.getCatalogue_id()
        );

        // ✅ Call circulation-service (expects JSON)
        Map<String, Object> response = rest_template.getForObject(
                CIRCULATION_SERVICE_URL,
                Map.class,
                uriVars
        );

// ✅ Extract boolean value from JSON
        Boolean allowed = response != null && Boolean.TRUE.equals(response.get("exists"));

        if (!allowed) {
            throw new RuntimeException("Member has not borrowed this catalogue, cannot add review.");
        }


        // ✅ Prevent duplicate review from same member for same catalogue
        if (review_repository.existsByMember_idAndCatalogue_id(review.getMember_id(), review.getCatalogue_id())) {
            throw new RuntimeException("Review already exists for this member and catalogue. Please update instead.");
        }

        // ✅ Generate ID
        review.setId(IdGenerator.next("R"));

        Review saved_review = review_repository.save(review);

        // ✅ Update catalogue rating
        update_catalogue_rating(review.getCatalogue_id());

        return saved_review;
    }

    public Review update_review(String id, Review updated_review) {
        Optional<Review> existing = review_repository.findById(id);

        if (existing.isEmpty()) {
            throw new RuntimeException("Review not found with id " + id);
        }

        Review review = existing.get();
        review.setComment(updated_review.getComment());
        review.setRating(updated_review.getRating());

        Review saved_review = review_repository.save(review);

        // ✅ Update catalogue rating
        update_catalogue_rating(review.getCatalogue_id());

        return saved_review;
    }

    public void delete_review(String id) {
        Optional<Review> existing = review_repository.findById(id);

        if (existing.isPresent()) {
            String catalogue_id = existing.get().getCatalogue_id();
            review_repository.deleteById(id);

            // ✅ Update catalogue rating
            update_catalogue_rating(catalogue_id);
        } else {
            throw new RuntimeException("Review not found with id " + id);
        }
    }

    public List<Review> get_reviews_by_catalogue(String catalogue_id) {
        return review_repository.findByCatalogue_id(catalogue_id);
    }

    public List<Review> get_reviews_by_member(String member_id) {
        return review_repository.findByMember_id(member_id);
    }

    // ------------------------------
    // ✅ Private helper
    // ------------------------------
    private void update_catalogue_rating(String catalogue_id) {
        List<Review> reviews = review_repository.findByCatalogue_id(catalogue_id);

        double avg = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        Map<String, String> uriVars = Map.of("catalogue_id", catalogue_id);
        rest_template.put(CATALOGUE_SERVICE_URL, Map.of("rating", avg), uriVars);

    }

    public List<Review> findAll() {
        return review_repository.findAll();
    }

    public Optional<Review> findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return Optional.empty();
        }
        return review_repository.findById(id.trim());    }

    public boolean softDeleteById(String id) {
        Optional<Review> opt = review_repository.findById(id);
        if (opt.isEmpty()) return false;

        Review r = opt.get();
        if (r.isDeleted()) return false; // already deleted

        r.setDeleted(true);
        review_repository.save(r);
        return true;
    }

}
