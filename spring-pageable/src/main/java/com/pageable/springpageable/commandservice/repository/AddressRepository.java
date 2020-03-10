package com.pageable.springpageable.commandservice.repository;

import com.pageable.springpageable.commandservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>,
        PagingAndSortingRepository<Address,Integer> {

}
