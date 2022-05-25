package com.example.commentproducer.service;

import com.example.commentproducer.pojo.CommentRequestPayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class RabbitMQProducerServiceTest {
    @InjectMocks
    private RabbitMQProducerService rabbitMQProducerService;
    @Mock
    private AmqpTemplate rabbitTemplate;
    @Test
    public void test(){
        CommentRequestPayload commentRequestPayload = new CommentRequestPayload();
        commentRequestPayload.setComment("test");
        commentRequestPayload.setFirstName("firstName");
        commentRequestPayload.setLastName("LastName");
        rabbitMQProducerService.send(commentRequestPayload);
    }

}
