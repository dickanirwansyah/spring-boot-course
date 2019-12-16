package com.dicka.examplethymeleaf.service.impl;

import com.dicka.examplethymeleaf.dto.RequestVendor;
import com.dicka.examplethymeleaf.model.Vendor;
import com.dicka.examplethymeleaf.repository.VendorRepository;
import com.dicka.examplethymeleaf.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Service
@Slf4j
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public Vendor createVendor(RequestVendor requestVendor) {

        Vendor vendor = new Vendor();
        vendor.setFirstName(requestVendor.getFirstName());
        vendor.setLastName(requestVendor.getLastName());
        vendor.setVendorType(requestVendor.getVendorType());
        vendor.setNpwp(requestVendor.getNpwp());
        vendor.setBankAccount(requestVendor.getBackAccount());
        vendor.setBankName(requestVendor.getBankName());
        vendor.setCreatedAt(new Date());
        vendor.setUpdatedAt(new Date());

        if (requestVendor.getNpwpFile()!=null){
            byte[] imageFileNpwp = null;
            try{
                imageFileNpwp = requestVendor.getNpwpFile().getBytes();
            }catch (IOException e){
                log.error(e.getLocalizedMessage());
            }

            if (imageFileNpwp.length > 0 && imageFileNpwp != null){
                vendor.setNpwpFile(imageFileNpwp);
            }else{
                vendor.setNpwpFile(null);
            }
        }

        if (requestVendor.getCoverBankAcc()!=null){
            byte[] imageFileCoverBankAcc = null;
            try{
                imageFileCoverBankAcc = requestVendor.getCoverBankAcc().getBytes();
            }catch (IOException e){
                log.error(e.getLocalizedMessage());
            }

            if (imageFileCoverBankAcc.length > 0 && imageFileCoverBankAcc != null){
                vendor.setCoverBankAcc(imageFileCoverBankAcc);
            }else{
                vendor.setCoverBankAcc(null);
            }
        }

        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, RequestVendor requestVendor) {
        return null;
    }

    @Override
    public ArrayList<Vendor> listVendor() {
       ArrayList<Vendor> vendors = new ArrayList<>();
       for (Vendor vendor : vendorRepository.findAll()){
           vendors.add(vendor);
       }
       return vendors;
    }

    @Override
    public Vendor findVendorById(Long id) {
        return vendorRepository.findVendorById(id);
    }
}
