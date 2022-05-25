package com.example.commentproducer.model;

import com.example.commentproducer.config.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;
    @Indexed
    private String personId;
    private String comment;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime createdTime;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime updatedTime;
}

