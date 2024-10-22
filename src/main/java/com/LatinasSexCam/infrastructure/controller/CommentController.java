package com.LatinasSexCam.infrastructure.controller;

import com.LatinasSexCam.domain.model.Comment;
import com.LatinasSexCam.infrastructure.dto.request.CommentRequest;
import com.LatinasSexCam.infrastructure.dto.response.CommentResponseDTO;
import com.LatinasSexCam.infrastructure.entity.CommentEntity;
import com.LatinasSexCam.infrastructure.repository.CommentJpaRepository;
import com.LatinasSexCam.application.service.CommentService;
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
    private final CommentJpaRepository commentJpaRepository;

    @PostMapping(value = "newComment")
    @CrossOrigin(origins = "*")
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
    public ResponseEntity<List<CommentResponseDTO>> getComments(){
        List<CommentResponseDTO> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/editComment/{id}")
    @CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "*")
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

    @DeleteMapping("/admin/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteCommentByAdmin(@PathVariable Long id){
        return commentService.deleteCommentByAdmin(id);
    }


}
