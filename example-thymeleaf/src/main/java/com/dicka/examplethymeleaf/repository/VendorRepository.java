package com.dicka.examplethymeleaf.repository;

import com.dicka.examplethymeleaf.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findVendorById(Long id);
}
