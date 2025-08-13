package com.lms.service;

import com.lms.entity.Admin;
import com.lms.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public String register(Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail())) {
            return "ಈ ಇಮೇಲ್ ಈಗಾಗಲೇ ಬಳಕೆಯಲ್ಲಿದೆ";
        }
        if (adminRepository.existsByPhone(admin.getPhone())) {
            return "ಈ ಫೋನ್ ನಂಬರು ಈಗಾಗಲೇ ಬಳಕೆಯಲ್ಲಿದೆ";
        }
        adminRepository.save(admin);
        return "ನೋಂದಣಿ ಯಶಸ್ವಿಯಾಗಿದೆ";
    }

    public String login(String email, String password) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (!adminOpt.isPresent()) {
            return "ಅಕೌಂಟ್ ಕಂಡುಬಂದಿಲ್ಲ";
        }
        if (!adminOpt.get().getPassword().equals(password)) {
            return "ನೀವು ನೀಡಿದ ಮಾಹಿತಿ ತಪ್ಪಾಗಿದೆ";
        }
        return "ಲಾಗಿನ್ ಯಶಸ್ವಿಯಾಗಿದೆ";
    }
}
