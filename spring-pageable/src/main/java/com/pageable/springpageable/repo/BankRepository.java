package com.pageable.springpageable.repo;

import com.pageable.springpageable.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Integer>,
        PagingAndSortingRepository<Bank, Integer> {

    Bank findById(int id);
    Bank findByBankName(String bankName);
}
