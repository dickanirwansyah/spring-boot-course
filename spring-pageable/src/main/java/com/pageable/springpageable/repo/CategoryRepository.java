package com.pageable.springpageable.repo;

import com.pageable.springpageable.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>,
        PagingAndSortingRepository<Category, Integer> {

    Optional<Category> findByName(String name);
    Category findById(int id);

}
