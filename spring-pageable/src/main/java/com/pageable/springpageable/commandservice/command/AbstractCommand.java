package com.pageable.springpageable.commandservice.command;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.validation.Validator;


/** abstract class **/

public abstract class AbstractCommand<RESULT, REQUEST extends ServiceRequest>
    implements Command<RESULT, REQUEST>, ApplicationContextAware, InitializingBean {

    protected Validator validator;

    protected ApplicationContext applicationContext;

    @Override
    public final RESULT execute(REQUEST request) {
        return doExecute(request);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = applicationContext.getBean(Validator.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public abstract RESULT doExecute(REQUEST request);
}
