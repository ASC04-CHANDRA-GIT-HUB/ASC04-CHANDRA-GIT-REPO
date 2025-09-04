package com.lms.admin.service;
import org.springframework.stereotype.Service;
import com.lms.admin.repository.AdminRepository;
import com.lms.admin.model.Admin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Value("${lock.threshold:3}") private int threshold;
    @Value("${lock.duration.minutes:30}") private long lockMinutes;

    public AdminService(AdminRepository repo){ this.repo = repo; }

    public Admin register(Admin a){
        a.setPasswordHash(encoder.encode(a.getPasswordHash()));
        return repo.save(a);
    }

    public Optional<Admin> findByEmail(String email){ return repo.findByEmail(email); }

    public boolean passwordMatches(Admin a, String plain){ return encoder.matches(plain, a.getPasswordHash()); }

    public void recordFailedAttempt(Admin a){
        a.setFailedAttempts(a.getFailedAttempts()+1);
        if(a.getFailedAttempts() >= threshold){
            a.setDisabled(true);
            a.setLockoutTime(LocalDateTime.now());
        }
        repo.save(a);
    }

    public void resetAttempts(Admin a){
        a.setFailedAttempts(0); a.setDisabled(false); a.setLockoutTime(null); repo.save(a);
    }

    public boolean isLocked(Admin a){
        if(!a.isDisabled() || a.getLockoutTime() == null) return false;
        Duration diff = Duration.between(a.getLockoutTime(), LocalDateTime.now());
        if(diff.toMinutes() >= lockMinutes){
            // unlock
            a.setDisabled(false); a.setFailedAttempts(0); a.setLockoutTime(null); repo.save(a);
            return false;
        }
        return true;
    }
}
