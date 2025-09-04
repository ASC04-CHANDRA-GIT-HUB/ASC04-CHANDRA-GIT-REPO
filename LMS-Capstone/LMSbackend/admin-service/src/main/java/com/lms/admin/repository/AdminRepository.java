package com.lms.admin.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lms.admin.model.Admin;
import java.util.Optional;
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
