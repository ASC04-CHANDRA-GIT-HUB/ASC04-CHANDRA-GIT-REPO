package com.lms.catalogueservice.controller;


import com.lms.catalogueservice.model.Catalogue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.lms.catalogueservice.repository.CatalogueRepository;
import com.lms.common.util.IdGenerator;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/catalogues")
public class CatalogueController {

    // DTO inside CatalogueController
    public static class RatingRequest {
        private Double rating;

        public Double getRating() {
            return rating;
        }
        public void setRating(Double rating) {
            this.rating = rating;
        }
    }


    private final Logger logger = LoggerFactory.getLogger(CatalogueController.class);

    @Autowired
    private CatalogueRepository repo;

    @GetMapping
    public ResponseEntity<Optional<Catalogue>> all() {
        Optional<Catalogue> list = repo.findByDeletedFalse();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Catalogue c) {
        try {
            // Normalize title/author when checking duplicates
            String title = c.getTitle() == null ? "" : c.getTitle().trim();
            String author = c.getAuthor() == null ? "" : c.getAuthor().trim();

            Optional<Catalogue> existing = repo.findByTitleAndAuthor(title, author);

            if (existing.isPresent()) {
                Catalogue book = existing.get();
                if (!book.isDeleted()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(Collections.singletonMap("message", "Book already exists with id " + book.getId()));

                }
                // Reuse ID for deleted book
                book.setDeleted(false);
                book.setAvailable(true);
                book.setDescription(c.getDescription());
                book.setTitle(title);
                book.setAuthor(author);
                repo.save(book);
                return ResponseEntity.ok(book);
            }

            // New book -> generate ID
            String id = IdGenerator.next("C");
            c.setId(id);
            // sanitize fields
            c.setTitle(title);
            c.setAuthor(author);
            if (c.getDescription() == null) c.setDescription("");
            repo.save(c);
            return ResponseEntity.status(HttpStatus.CREATED).body(c);

        } catch (Exception ex) {
            logger.error("Error creating catalogue: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Server error", "details", ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            if (id == null || id.trim().isEmpty()) return ResponseEntity.badRequest().body(Map.of("message","id required"));
            String key = id.trim().toUpperCase(); // normalize
            Optional<Catalogue> opt = repo.findById(key);
            if (opt.isEmpty() || opt.get().isDeleted()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Not found"));
            }
            return ResponseEntity.ok(opt.get());
        } catch (Exception ex) {
            logger.error("Error fetching catalogue {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message","Server error", "details", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Catalogue updated) {
        try {
            if (id == null || id.trim().isEmpty()) return ResponseEntity.badRequest().body(Map.of("message","id required"));
            String key = id.trim().toUpperCase();
            Optional<Catalogue> opt = repo.findById(key);
            if (opt.isEmpty() || opt.get().isDeleted()) return ResponseEntity.notFound().build();

            Catalogue c = opt.get();
            if (updated.getTitle() != null) c.setTitle(updated.getTitle().trim());
            if (updated.getAuthor() != null) c.setAuthor(updated.getAuthor().trim());
            if (updated.getDescription() != null) c.setDescription(updated.getDescription());
            c.setAvailable(updated.isAvailable());
            repo.save(c);
            return ResponseEntity.ok(c);
        } catch (Exception ex) {
            logger.error("Error updating catalogue {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message","Server error", "details", ex.getMessage()));
        }
    }

    // inside CatalogueController
    @PutMapping("/{id}/rating")
    public ResponseEntity<?> updateRating(
            @PathVariable("id") String id,
            @RequestBody RatingRequest request) {

        try {
            if (id == null || id.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "id required"));
            }

            String key = id.trim().toUpperCase();
            Optional<Catalogue> opt = repo.findById(key);
            if (opt.isEmpty() || opt.get().isDeleted()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Catalogue not found"));
            }

            Catalogue catalogue = opt.get();
            catalogue.setRating(request.getRating());// ✅ assumes Catalogue entity has a `rating` field (Double)
            repo.save(catalogue);

            return ResponseEntity.ok(
                    Map.of("message", "Rating updated successfully",
                            "catalogue_id", key,
                            "new_rating", request.getRating())
            );

        } catch (Exception ex) {
            logger.error("Error updating rating for catalogue {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Server error", "details", ex.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        try {
            if (id == null || id.trim().isEmpty()) return ResponseEntity.badRequest().body(Map.of("message","id required"));
            String key = id.trim().toUpperCase();
            Optional<Catalogue> opt = repo.findById(key);
            if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Not found"));
            Catalogue e = opt.get();
            if (e.isDeleted()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Already deleted"));
            }
            e.setDeleted(true);
            e.setAvailable(false);
            repo.save(e);
            return ResponseEntity.ok(Map.of("message","Book " + key + " deleted successfully"));
        } catch (Exception ex) {
            logger.error("Error deleting catalogue {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message","Server error", "details", ex.getMessage()));
        }
    }
}
