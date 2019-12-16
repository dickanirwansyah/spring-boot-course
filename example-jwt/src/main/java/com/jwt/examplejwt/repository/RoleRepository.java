package com.jwt.examplejwt.repository;

import com.jwt.examplejwt.entity.Role;
import com.jwt.examplejwt.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
