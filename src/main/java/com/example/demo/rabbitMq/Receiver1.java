package com.example.demo.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "mall.order.cancel")
public class Receiver1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver1.class);

    @RabbitHandler
    public void handle(String message){
        LOGGER.info("process message:{}",message);
    }
}
