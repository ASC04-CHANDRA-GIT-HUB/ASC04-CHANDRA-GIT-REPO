package com.lms.memberservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.lms.memberservice.model.Member;
import com.lms.memberservice.repository.MemberRepository;
import com.lms.common.util.IdGenerator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired private MemberRepository repo;

    @GetMapping
    public List<Member> all() { return repo.findByDeletedFalse(); }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Member m) {
        m.setId(IdGenerator.next("M"));
        repo.save(m);
        return ResponseEntity.ok(m);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            if (id == null || id.trim().isEmpty()) return ResponseEntity.badRequest().body(Map.of("message","id required"));
            String key = id.trim().toUpperCase(); // normalize
            Optional<Member> opt = repo.findById(key);
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
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Member updated) {
        return repo.findById(id).filter(m -> !m.isDeleted()).map(m -> {
            if (updated.getName() != null) m.setName(updated.getName());
            if (updated.getEmail() != null) m.setEmail(updated.getEmail());
            if (updated.getPhone() != null) m.setPhone(updated.getPhone());
            repo.save(m);
            return ResponseEntity.ok(m);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        try {
            if (id == null || id.trim().isEmpty()) return ResponseEntity.badRequest().body(Map.of("message","id required"));
            String key = id.trim().toUpperCase();
            Optional<Member> opt = repo.findById(key);
            if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Not found"));
            Member e = opt.get();
            if (e.isDeleted()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Already deleted"));
            }
            e.setDeleted(true);
            repo.save(e);
            return ResponseEntity.ok(Map.of("message","Book " + key + " deleted successfully"));
        } catch (Exception ex) {
            logger.error("Error deleting catalogue {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message","Server error", "details", ex.getMessage()));
        }
    }
}
