package com.example.commentproducer.service;

import com.example.commentproducer.model.Comment;
import com.example.commentproducer.model.Person;
import com.example.commentproducer.pojo.CommentRequestPayload;
import com.example.commentproducer.pojo.ResponseDto;
import com.example.commentproducer.repository.CommentRepository;
import com.example.commentproducer.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
    @InjectMocks
    private CommentService commentService;

    @Mock
    private RabbitMQProducerService rabbitMQProducerService;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private CommentRepository commentRepository;

    @Test
    public void testGetAll() {
        Pageable paging = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "createdTime"));
        List<Comment> commentList = new ArrayList<>();
        Comment comment = new Comment();
        comment.setComment("hello test");
        comment.setId("11");
        comment.setPersonId("13");
        commentList.add(comment);
        Page<Comment> page = new PageImpl<>(commentList, paging, 1);
        Mockito.when(commentRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        Person person = new Person();
        person.setId("13");
        person.setFirstName("Yon");
        person.setLastName("Mercurry");
        Mockito.when(personRepository.findAllByIdIn(Collections.singletonList("13"))).thenReturn(Collections.singletonList(person));
        ResponseDto responseDto = commentService.getAll(paging);
        Assert.assertNotNull(responseDto);
        Assert.assertNotNull(responseDto.getData());
    }

    @Test
    public void testSave() {
        CommentRequestPayload commentRequestPayload = new CommentRequestPayload();
        commentRequestPayload.setComment("test");
        commentRequestPayload.setFirstName("firstName");
        commentRequestPayload.setLastName("LastName");
        ResponseDto responseDto = commentService.save(commentRequestPayload);
        Assert.assertNotNull(responseDto);
        Assert.assertNull(responseDto.getData());
        Assert.assertEquals("Saved successfully",responseDto.getMessage());

    }
}
