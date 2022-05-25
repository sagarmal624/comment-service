package com.example.commentproducer.controller;

import com.example.commentproducer.pojo.CommentRequestPayload;
import com.example.commentproducer.pojo.ResponseDto;
import com.example.commentproducer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<ResponseDto> create(@RequestBody CommentRequestPayload commentRequestPayload) {
        return ResponseEntity.ok(commentService.save(commentRequestPayload));
    }

    @GetMapping("/comments")
    public ResponseEntity<ResponseDto> getComments(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        Pageable paging = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "createdTime"));
        return ResponseEntity.ok(commentService.getAll(paging));
    }
}
