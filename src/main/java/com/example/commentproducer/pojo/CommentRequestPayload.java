package com.example.commentproducer.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentRequestPayload {
    private String personId;
    private String firstName;
    private String lastName;
    private String comment;
}
