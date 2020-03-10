package com.pageable.springpageable.repo;

import com.pageable.springpageable.entity.Addres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddresRepository extends JpaRepository<Addres, Integer>,
        PagingAndSortingRepository<Addres, Integer> {

    Addres findByUserId(int id);
}
