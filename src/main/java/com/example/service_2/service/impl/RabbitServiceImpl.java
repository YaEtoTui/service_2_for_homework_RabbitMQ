package com.example.service_2.service.impl;

import com.example.service_2.service.RabbitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RabbitServiceImpl implements RabbitService {
    ObjectMapper objectMapper;
    RabbitTemplate rabbitTemplate;

    @Override
    @SneakyThrows
    public void send(Object message) {
        MessageProperties messageProperties = new MessageProperties();

        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);

        Message msg = new Message(objectMapper.writeValueAsBytes(message), messageProperties);

        rabbitTemplate.send("toy.queue", msg);

        System.out.println("Message sent 'Toy'");
    }
}
