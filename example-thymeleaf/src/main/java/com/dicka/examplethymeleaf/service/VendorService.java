package com.dicka.examplethymeleaf.service;

import com.dicka.examplethymeleaf.dto.RequestVendor;
import com.dicka.examplethymeleaf.model.Vendor;

import java.util.ArrayList;

public interface VendorService {
    Vendor createVendor(RequestVendor requestVendor);
    Vendor updateVendor(Long id, RequestVendor requestVendor);
    ArrayList<Vendor> listVendor();
    Vendor findVendorById(Long id);
    RequestVendor findVendorByDtoId(Long id);
}
