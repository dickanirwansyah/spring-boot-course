package com.pageable.springpageable.commandservice.command;


public interface ServiceExecutor {

    <T, R extends ServiceRequest> T execute(Class<? extends Command<T, R>> commandClass, R request);

}
