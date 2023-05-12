package com.example.service_2.service;

import com.example.service_2.adapter.web.listener.CreatedToyListener;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RabbitListenerService {
    RabbitService rabbitService;

    @RabbitListener(queues = {"toy.queue"})
    public void listen(@Payload CreatedToyListener toyListener) {
        System.out.printf("Toy with name '%s' and count '%s'\n", toyListener.getName(), toyListener.getCount());
    }
}
