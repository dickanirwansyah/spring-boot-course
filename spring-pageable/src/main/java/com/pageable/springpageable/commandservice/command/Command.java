package com.pageable.springpageable.commandservice.command;


public interface Command<RESULT, REQUEST extends ServiceRequest> {

    RESULT execute(REQUEST request);

}
