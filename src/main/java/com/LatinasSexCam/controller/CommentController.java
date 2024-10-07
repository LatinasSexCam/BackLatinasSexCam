package com.LatinasSexCam.controller;

import com.LatinasSexCam.model.Comment;
import com.LatinasSexCam.model.request.CommentRequest;
import com.LatinasSexCam.repository.CommentRepository;
import com.LatinasSexCam.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("LatinasSexCam")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping(value = "newComment")
    public ResponseEntity<String> saveComment(@RequestBody CommentRequest request) {
        Comment comment = commentService.saveComment(request);
        String responseMessage;
        HttpStatus status;

        if (comment != null) {
            responseMessage = "Comentario registrado con éxito";
            status = HttpStatus.CREATED;
        } else {
            responseMessage = "Usuario no encontrado";
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(responseMessage, status);
    }

    @GetMapping("comments")
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    @PutMapping("/editComment/{id}")
    public ResponseEntity<String> editComment(@PathVariable Long id, @RequestBody CommentRequest request) {
        String responseMessage;
        HttpStatus status;

        try {
            commentService.editComment(id, request);

            responseMessage = "Comentario editado con éxito";
            status = HttpStatus.OK;

        } catch (NoSuchElementException e) {
            responseMessage = e.getMessage();
            status = HttpStatus.NOT_FOUND;

        } catch (IllegalStateException e) {
            responseMessage = e.getMessage();
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(responseMessage, status);
    }
    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id, @RequestBody CommentRequest email) {
        String responseMessage;
        HttpStatus status;

        try {
            commentService.deleteCommentByUser(id, email);

            responseMessage = "Comentario eliminado con éxito";
            status = HttpStatus.OK;

        } catch (NoSuchElementException e) {
            responseMessage = e.getMessage();
            status = HttpStatus.NOT_FOUND;

        } catch (IllegalStateException e) {
            responseMessage = e.getMessage();
            status = HttpStatus.FORBIDDEN;

        }

        return new ResponseEntity<>(responseMessage, status);
    }


}
