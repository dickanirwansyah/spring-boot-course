package com.pageable.springpageable.commandservice.service.impl;

import com.pageable.springpageable.commandservice.command.AbstractCommand;
import com.pageable.springpageable.commandservice.entity.Address;
import com.pageable.springpageable.commandservice.repository.AddressRepository;
import com.pageable.springpageable.commandservice.request.AddressInsertRequest;
import com.pageable.springpageable.commandservice.service.AddressInserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressInsertServiceImpl extends AbstractCommand<Address, AddressInsertRequest>
    implements AddressInserService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address doExecute(AddressInsertRequest request) {
        Address address = create(request);
        return addressRepository.save(address);
    }

    Address create(AddressInsertRequest request){
        return Address.builder()
                        .street(request.getStreet())
                        .zipCode(request.getZipCode())
                        .no(request.getNo())
                        .village(request.getVillage())
                        .city(request.getCity())
                        .country(request.getCountry())
                        .build();
    }
}
