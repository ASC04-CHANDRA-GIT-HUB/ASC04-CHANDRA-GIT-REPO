package com.vls.login.repository;

import com.vls.login.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginModel, Integer> {
}
