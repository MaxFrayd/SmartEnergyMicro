package com.reznikov.smartenergy.events.source;



import com.reznikov.smartenergy.dto.CustomerFullDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SimpleSourceBean {
    @Resource
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);



    public void publishCustomerChange(CustomerFullDto customer){

        source.output().send(MessageBuilder.withPayload(customer).build());
    }
}
