package com.lms.acquisitionservice.controller;

import com.lms.acquisitionservice.model.Acquisition;
import com.lms.acquisitionservice.repository.AcquisitionRepository;
import com.lms.common.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acquisitions")
public class AcquisitionController {

    @Autowired
    private AcquisitionRepository acquisitionRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String CATALOGUE_SERVICE_URL = "http://localhost:8083/catalogues";

    // Get all acquisitions
    @GetMapping
    public List<Acquisition> getAll() {
        return acquisitionRepository.findAll();
    }

    // Get acquisition by ID
    @GetMapping("/{id}")
    public ResponseEntity<Acquisition> getById(@PathVariable String id) {
        Optional<Acquisition> acq = acquisitionRepository.findById(id);
        return acq.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create new acquisition
    @PostMapping
    public Acquisition create(@RequestBody Acquisition acquisition) {
        if (acquisition.getId() == null || acquisition.getId().isEmpty()) {
            acquisition.setId(IdGenerator.next("A"));
        }
        return acquisitionRepository.save(acquisition);
    }

    // Update acquisition
    @PutMapping("/{id}")
    public ResponseEntity<Acquisition> update(@PathVariable String id, @RequestBody Acquisition acquisition) {
        Optional<Acquisition> existingOpt = acquisitionRepository.findById(id);
        if (!existingOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Acquisition existing = existingOpt.get();
        existing.setTitle(acquisition.getTitle());
        existing.setAuthor(acquisition.getAuthor());
        existing.setSupplier(acquisition.getSupplier());
        existing.setStatus(acquisition.getStatus());
        existing.setDeleted(acquisition.isDeleted());
        existing.setDescription(acquisition.getDescription());

        Acquisition saved = acquisitionRepository.save(existing);

        // Auto-send to catalogue-service if status is RECEIVED
        if ("RECEIVED".equalsIgnoreCase(saved.getStatus())) {
            try {
                // Build catalogue JSON
                CatalogueDto dto = new CatalogueDto();
                dto.setTitle(saved.getTitle());
                dto.setAuthor(saved.getAuthor());
                dto.setDescription(saved.getDescription());
                dto.setAvailable(true);

                restTemplate.postForEntity(CATALOGUE_SERVICE_URL, dto, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.ok(saved);
    }

    // Delete acquisition
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Optional<Acquisition> existing = acquisitionRepository.findById(id);
        if (!existing.isPresent()) return ResponseEntity.notFound().build();

        acquisitionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Inner DTO class for catalogue-service
    static class CatalogueDto {
        private String title;
        private String author;
        private String description;
        private boolean available;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public boolean isAvailable() { return available; }
        public void setAvailable(boolean available) { this.available = available; }
    }
}
