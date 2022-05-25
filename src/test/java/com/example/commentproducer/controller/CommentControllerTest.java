package com.example.commentproducer.controller;

import com.example.commentproducer.model.Comment;
import com.example.commentproducer.model.Person;
import com.example.commentproducer.pojo.CommentRequestPayload;
import com.example.commentproducer.pojo.CommentResponse;
import com.example.commentproducer.pojo.ResponseDto;
import com.example.commentproducer.service.CommentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
    @InjectMocks
    private CommentController commentController;
    @Mock
    private CommentService commentService;

    @Test
    public void testCreate() {
        CommentRequestPayload commentRequestPayload = new CommentRequestPayload();
        commentRequestPayload.setComment("test");
        commentRequestPayload.setFirstName("firstName");
        commentRequestPayload.setLastName("LastName");
        Mockito.when(commentService.save(Mockito.any())).thenReturn(ResponseDto.buildSuccess("saved"));
        ResponseEntity<ResponseDto> responseDto = commentController.create(commentRequestPayload);
        Assert.assertNotNull(responseDto);
        Assert.assertNotNull(responseDto.getBody());
        Assert.assertNotNull(responseDto.getBody().getMessage());
        Assert.assertEquals(responseDto.getBody().getMessage(), "saved");
    }

    @Test
    public void testGetComments() {
        List<CommentResponse> commentResponseList = new ArrayList<>();
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setComment(new Comment());
        commentResponse.setPerson(new Person());
        commentResponseList.add(commentResponse);
        Mockito.when(commentService.getAll(Mockito.any())).thenReturn(ResponseDto.buildSuccess(commentResponseList));
        ResponseEntity<ResponseDto> responseDto = commentController.getComments(0, 10);
        Assert.assertNotNull(responseDto);
        Assert.assertNotNull(responseDto.getBody());

    }
}
