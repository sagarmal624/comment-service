package com.example.commentproducer.service;

import com.example.commentproducer.pojo.CommentRequestPayload;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducerService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${comment.rabbitmq.exchange}")
    private String exchange;

    @Value("${comment.rabbitmq.routingkey}")
    private String routingkey;


    public void send(CommentRequestPayload comment) {
        rabbitTemplate.convertAndSend(exchange, routingkey, comment);
        System.out.println("Send msg = " + comment);
    }
}
