package com.vls.login.service;

import com.vls.exception.InvalidLoginException;
import com.vls.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean authenticate(Integer loginId, String password) {
        return loginRepository.findById(loginId)
                .map(login -> login.getPassword().equals(password))
                .orElseThrow(() -> new InvalidLoginException("Invalid Login ID"));
    }
}
