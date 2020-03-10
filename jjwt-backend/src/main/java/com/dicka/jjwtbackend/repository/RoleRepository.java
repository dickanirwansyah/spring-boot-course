package com.dicka.jjwtbackend.repository;

import com.dicka.jjwtbackend.entity.ERole;
import com.dicka.jjwtbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}
