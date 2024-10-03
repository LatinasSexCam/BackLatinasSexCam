package com.LatinasSexCam.controller;

import com.LatinasSexCam.model.Comment;
import com.LatinasSexCam.model.request.CommentRequest;
import com.LatinasSexCam.repository.CommentRepository;
import com.LatinasSexCam.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("LatinasSexCam")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping(value = "newComment")
    public ResponseEntity<String> comment(@RequestBody CommentRequest request){
        return commentService.saveComment(request);
    }

    @GetMapping("comments")
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }
}
