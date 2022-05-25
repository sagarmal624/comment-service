package com.example.commentproducer.service;

import com.example.commentproducer.model.Comment;
import com.example.commentproducer.model.Person;
import com.example.commentproducer.pojo.CommentRequestPayload;
import com.example.commentproducer.pojo.CommentResponse;
import com.example.commentproducer.pojo.Pagination;
import com.example.commentproducer.pojo.ResponseDto;
import com.example.commentproducer.repository.CommentRepository;
import com.example.commentproducer.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private RabbitMQProducerService rabbitMQProducerService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CommentRepository commentRepository;

        public ResponseDto save(CommentRequestPayload commentRequestPayload) {
        rabbitMQProducerService.send(commentRequestPayload);
        return ResponseDto.buildSuccess("Saved successfully");
    }

    public ResponseDto getAll(Pageable paging) {
        Page<Comment> commentPage = commentRepository.findAll(paging);
        List<Comment> commentList = commentPage.getContent();
        List<String> personIds = commentList.stream().map(Comment::getPersonId).collect(Collectors.toList());
        List<Person> personList = personRepository.findAllByIdIn(personIds);
        List<CommentResponse> commentResponseList = commentList.stream().map(comment -> {
            CommentResponse commentDto = new CommentResponse();
            commentDto.setComment(comment);
            commentDto.setPerson(personList.stream().filter(it -> it.getId().equals(comment.getPersonId())).findFirst().get());
            return commentDto;
        }).collect(Collectors.toList());
        Pagination pagination = Pagination.builder().items(commentResponseList).size(commentPage.getSize()).totalPages(commentPage.getTotalPages()).totalElements(commentPage.getTotalElements()).build();
        return ResponseDto.buildSuccess(pagination);
    }
}
