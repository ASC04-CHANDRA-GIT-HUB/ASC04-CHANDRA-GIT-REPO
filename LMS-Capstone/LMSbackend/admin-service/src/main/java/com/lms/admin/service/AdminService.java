package com.lms.admin.service;

import org.springframework.stereotype.Service;
import com.lms.admin.repository.AdminRepository;
import com.lms.admin.model.Admin;
import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository repo;

    @Value("${lock.threshold:3}")
    private int threshold;

    @Value("${lock.duration.minutes:30}")
    private long lockMinutes;

    public AdminService(AdminRepository repo) {
        this.repo = repo;
    }

    // Register admin WITHOUT hashing password
    public Admin register(Admin a) {
        // plain password, no encoding
        return repo.save(a);
    }

    // Find admin by email
    public Optional<Admin> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    // Check if plain password matches stored password
    public boolean passwordMatches(Admin a, String plain) {
        return a.getPassword().equals(plain);
    }

    // Record a failed login attempt and lock account if threshold exceeded
    public void recordFailedAttempt(Admin a) {
        a.setFailedAttempts(a.getFailedAttempts() + 1);
        if (a.getFailedAttempts() >= threshold) {
            a.setDisabled(true);
            a.setLockoutTime(LocalDateTime.now());
        }
        repo.save(a);
    }

    // Reset failed attempts and unlock account
    public void resetAttempts(Admin a) {
        a.setFailedAttempts(0);
        a.setDisabled(false);
        a.setLockoutTime(null);
        repo.save(a);
    }

    // Check if account is currently locked
    public boolean isLocked(Admin a) {
        if (!a.isDisabled() || a.getLockoutTime() == null) return false;

        Duration diff = Duration.between(a.getLockoutTime(), LocalDateTime.now());
        if (diff.toMinutes() >= lockMinutes) {
            // Unlock account after lock duration
            a.setDisabled(false);
            a.setFailedAttempts(0);
            a.setLockoutTime(null);
            repo.save(a);
            return false;
        }

        return true;
    }
}
