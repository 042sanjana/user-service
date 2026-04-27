package com.ewallet.user_service.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {
    @RabbitListener(queues="user.registered.queue")
    public void consume(String message){
        System.out.println("Recieved:"+message);

    }
}
