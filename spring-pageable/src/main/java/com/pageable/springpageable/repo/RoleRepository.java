package com.pageable.springpageable.repo;

import com.pageable.springpageable.entity.Role;
import com.pageable.springpageable.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>,
        PagingAndSortingRepository<Role, Integer> {

    Optional<Role> findByName(RoleName name);
}
