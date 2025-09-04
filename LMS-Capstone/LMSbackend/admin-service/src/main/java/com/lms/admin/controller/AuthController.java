package com.lms.admin.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.lms.admin.dto.AuthDtos.*;
import com.lms.admin.model.Admin;
import com.lms.admin.repository.AdminRepository;
import com.lms.admin.security.JwtUtil;
import com.lms.admin.service.AdminService;
import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private AdminRepository repo;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq req){
        if(repo.existsByEmail(req.email())) return ResponseEntity.badRequest().body(Map.of("message","Email already used"));
        if(repo.existsByPhone(req.phone())) return ResponseEntity.badRequest().body(Map.of("message","Phone already used"));
        // basic name characters check
        if(!req.name().matches("[A-Za-z ]+")) return ResponseEntity.badRequest().body(Map.of("message","Name invalid"));
        // password pattern: at least upper, lower, digit, special
        if(!req.password().matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}"))
            return ResponseEntity.badRequest().body(Map.of("message","Password must contain upper, lower, digit and special char"));
        Admin a = new Admin();
        a.setName(req.name()); a.setEmail(req.email()); a.setPhone(req.phone()); a.setPasswordHash(req.password());
        adminService.register(a);
        return ResponseEntity.ok(Map.of("message","Registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req){
        var opt = repo.findByEmail(req.email());
        if(opt.isEmpty()) return ResponseEntity.status(401).body(Map.of("message","Invalid credentials"));
        var a = opt.get();
        if(adminService.isLocked(a)) return ResponseEntity.status(403).body(Map.of("message","Account locked. Try later."));
        if(!adminService.passwordMatches(a, req.password())){
            adminService.recordFailedAttempt(a);
            return ResponseEntity.status(401).body(Map.of("message","Invalid credentials"));
        }
        adminService.resetAttempts(a);
        String token = jwtUtil.generate(a.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
