package com.example.demo.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = "mall.order.cancel.ttl")
    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("process orderId:{}",orderId);
    }

    @RabbitListener(queues = "mall.order.cancel")
    @RabbitHandler
    public void handle(String message){
        LOGGER.info("process message:{}",message);
    }
}
