package com.example.commentproducer.pojo;

import com.example.commentproducer.model.Comment;
import com.example.commentproducer.model.Person;
import lombok.Data;

@Data
public class CommentResponse {
    private Person person;
    private Comment comment;
}
