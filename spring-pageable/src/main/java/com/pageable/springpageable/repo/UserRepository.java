package com.pageable.springpageable.repo;

import com.pageable.springpageable.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>,
        PagingAndSortingRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    User findUserByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByEmail(String email);
}
