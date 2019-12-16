package com.jwt.examplejwt.repository;

import com.jwt.examplejwt.entity.Leads;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeadsRepository extends JpaRepository<Leads, Long> {
    Optional<Leads> findByPhone(String phone);
    Optional<Leads> findByEmail(String email);
}
