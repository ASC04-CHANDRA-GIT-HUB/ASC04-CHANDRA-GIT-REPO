package com.lms.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.lms.admin.dto.AuthDtos.*;
import com.lms.admin.model.Admin;
import com.lms.admin.repository.AdminRepository;
import com.lms.admin.service.AdminService;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AdminRepository repo;

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq req) {
        // Check if email or phone already exists
        if(repo.existsByEmail(req.email()))
            return ResponseEntity.badRequest().body(Map.of("message","Email already used"));
        if(repo.existsByPhone(req.phone()))
            return ResponseEntity.badRequest().body(Map.of("message","Phone already used"));

        // Validate name (letters and spaces only)
        if(!req.name().matches("[A-Za-z ]+"))
            return ResponseEntity.badRequest().body(Map.of("message","Name invalid"));

        // Validate password (upper, lower, digit, special char)
        if(!req.password().matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}"))
            return ResponseEntity.badRequest().body(Map.of("message","Password must contain upper, lower, digit and special char"));

        // Save admin
        Admin a = new Admin();
        a.setName(req.name());
        a.setEmail(req.email());
        a.setPhone(req.phone());
        a.setPassword(req.password());  // <-- updated to plain password
        adminService.register(a);

        return ResponseEntity.ok(Map.of("message","Registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {
        var opt = repo.findByEmail(req.email());
        if(opt.isEmpty())
            return ResponseEntity.status(401).body(Map.of("message","Invalid credentials"));

        var a = opt.get();

        // Check if account is locked
        if(adminService.isLocked(a))
            return ResponseEntity.status(403).body(Map.of("message","Account locked. Try later."));

        // Check password
        if(!adminService.passwordMatches(a, req.password())){
            adminService.recordFailedAttempt(a);
            return ResponseEntity.status(401).body(Map.of("message","Invalid credentials"));
        }

        adminService.resetAttempts(a);

        // JWT removed, just return success
        return ResponseEntity.ok(Map.of("message","Login Successful"));
    }
}
