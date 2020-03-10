package com.pageable.springpageable.repo;

import com.pageable.springpageable.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student, Integer>,
        JpaRepository<Student, Integer> {

    Page<Student> findAll(Pageable pageable);
}
