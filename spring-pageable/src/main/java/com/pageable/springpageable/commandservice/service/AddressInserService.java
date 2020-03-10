package com.pageable.springpageable.commandservice.service;

import com.pageable.springpageable.commandservice.command.Command;
import com.pageable.springpageable.commandservice.entity.Address;
import com.pageable.springpageable.commandservice.request.AddressInsertRequest;

public interface AddressInserService extends Command<Address, AddressInsertRequest> {
}
