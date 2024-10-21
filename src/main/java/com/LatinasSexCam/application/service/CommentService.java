package com.LatinasSexCam.application.service;


import com.LatinasSexCam.domain.model.Comment;
import com.LatinasSexCam.domain.model.User;
import com.LatinasSexCam.domain.ports.CommentRepositoryPort;
import com.LatinasSexCam.domain.ports.UserRepositoryPort;
import com.LatinasSexCam.infrastructure.dto.request.CommentRequest;
import com.LatinasSexCam.infrastructure.dto.response.CommentResponseDTO;
import com.LatinasSexCam.infrastructure.repository.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepositoryPort userRepositoryPort;
    private final CommentRepositoryPort commentRepositoryPort;

    public Comment saveComment(CommentRequest request) {
        User userExist = userRepositoryPort.findByEmail(request.getEmail());
        LocalDateTime now = LocalDateTime.now();

        if (userExist != null) {
            Comment comment = Comment.builder()
                    .stars(request.getStars())
                    .comment(request.getComment())
                    .user(userExist)
                    .createdAt(now)
                    .build();

            commentRepositoryPort.save(comment);
            return comment;
        } else {
            return null;
        }
    }

    public void editComment(Long commentId, CommentRequest request) {
        User userExist = userRepositoryPort.findByEmail(request.getEmail());

        if (userExist != null) {
            Optional<Comment> existingCommentOpt = commentRepositoryPort.findById(commentId);

            if (existingCommentOpt.isPresent()) {
                Comment existingComment = existingCommentOpt.get();


                if (existingComment.getUser().getIdUser() == userExist.getIdUser()) {
                    existingComment.setStars(request.getStars());
                    existingComment.setComment(request.getComment());
                    existingComment.setCreatedAt(LocalDateTime.now());

                    commentRepositoryPort.save(existingComment);
                } else {
                    throw new IllegalStateException("Sin permiso para editar este comentario");
                }
            } else {
                throw new NoSuchElementException("Comentario no encontrado");
            }
        } else {
            throw new NoSuchElementException("Usuario no encontrado");
        }
    }


    public void deleteCommentByUser(Long commentId, CommentRequest request) {

        Optional<Comment> existingComment = commentRepositoryPort.findById(commentId);

        User user = userRepositoryPort.findByEmail(request.getEmail());


        if (!existingComment.isPresent()) {
            throw new NoSuchElementException("Comentario no encontrado");
        }

        if (user == null) {
            throw new NoSuchElementException("Usuario no encontrado");
        }

        if (existingComment.get().getUser().getIdUser() != user.getIdUser()) {
            throw new IllegalStateException("No puedes eliminar este comentario");
        }

        commentRepositoryPort.delete(existingComment.get());
    }

    public ResponseEntity<String> deleteCommentByAdmin(Long commentId){
        Optional<Comment> existingComment = commentRepositoryPort.findById(commentId);

        if (existingComment.isPresent()){
            commentRepositoryPort.delete(existingComment.get());
            return new ResponseEntity<>("Comentario eliminado por un admin", HttpStatus.OK);
        }
        return new ResponseEntity<>("Comentario no encontrado", HttpStatus.NOT_FOUND);
    }

    public List<CommentResponseDTO> getAllComments() {
        List<Comment> comments = commentRepositoryPort.findAllComments();
        return comments.stream()
                .map(CommentResponseDTO::new)
                .collect(Collectors.toList());
    }

}
