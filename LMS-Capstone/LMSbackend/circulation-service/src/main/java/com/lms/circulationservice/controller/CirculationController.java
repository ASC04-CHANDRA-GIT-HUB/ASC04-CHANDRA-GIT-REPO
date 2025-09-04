package com.lms.circulationservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.lms.circulationservice.model.Circulation;
import com.lms.circulationservice.repository.CirculationRepository;
import com.lms.common.util.IdGenerator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/circulations")
public class CirculationController {

    @Autowired
    private CirculationRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Circulation> all() {
        return repo.findByDeletedFalse();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Circulation c) {
        boolean memberValid = true;
        boolean catalogueValid = true;

        // ✅ Check member existence
        try {
            String memberUrl = "http://localhost:8084/members/" + c.getMember_id();
            restTemplate.getForEntity(memberUrl, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            memberValid = false;
        }

        // ✅ Check catalogue existence
        try {
            String catalogueUrl = "http://localhost:8083/catalogues/" + c.getCatalogue_id();
            restTemplate.getForEntity(catalogueUrl, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            catalogueValid = false;
        }

        // ✅ Handle errors clearly
        if (!memberValid || !catalogueValid) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Validation failed");

            if (!memberValid) {
                error.put("Invalid member id","Member with id " + c.getMember_id() + " not found");
            }
            if (!catalogueValid) {
                error.put("Invalid catalogue id", "Catalogue with id " + c.getCatalogue_id() + " not found");
            }

            return ResponseEntity.status(404).body(error);
        }

        // ✅ Already issued checks
        if (repo.findByCatalogue_idAndMember_idAndStatus(
                c.getCatalogue_id(), c.getMember_id(), "ISSUED").isPresent()) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Book already issued to this member with same ID.")
            );
        }

        if (repo.findByCatalogue_idAndStatus(c.getCatalogue_id(), "ISSUED").isPresent()) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Book already issued to another member.")
            );
        }

        // ✅ Assign ID, issue & due dates
        c.setId(IdGenerator.next("T"));
        LocalDate issue_date = (c.getIssue_date() != null) ? c.getIssue_date() : LocalDate.now();
        c.setIssue_date(issue_date);
        c.setDue_date(issue_date.plusDays(10));
        c.setStatus("ISSUED");

        repo.save(c);
        return ResponseEntity.ok(c);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        return repo.findById(id).filter(e -> !e.isDeleted())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable("id") String id) {
        return repo.findById(id)
                .filter(c -> !c.isDeleted() && "ISSUED".equals(c.getStatus()))
                .map(c -> {
                    c.setStatus("RETURNED");
                    c.setReturn_date(LocalDate.now());
                    repo.save(c);
                    return ResponseEntity.ok(Map.of("message", "book returned successfully"));
                })
                .orElse(ResponseEntity.badRequest().body(Map.of("error", "book not found or already returned")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return repo.findById(id)
                .map(c -> {
                    c.setDeleted(true);
                    repo.save(c);
                    return ResponseEntity.ok(Map.of("message", "circulation " + id + " deleted"));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exists")
    public ResponseEntity<?> existsCirculation(
            @RequestParam String member_id,
            @RequestParam String catalogue_id) {

        boolean exists = repo.existsByMember_idAndCatalogue_idAndStatus(member_id, catalogue_id, "RETURNED");

        if (exists) {
            return ResponseEntity.ok(Map.of(
                    "exists", true,
                    "message", "Circulation found: member has borrowed this book"
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "exists", false,
                    "message", "No active circulation found for this member and book"
            ));
        }
    }

}
