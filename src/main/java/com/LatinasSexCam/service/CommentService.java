package com.LatinasSexCam.service;


import com.LatinasSexCam.model.Comment;
import com.LatinasSexCam.model.User;
import com.LatinasSexCam.model.request.CommentRequest;
import com.LatinasSexCam.repository.CommentRepository;
import com.LatinasSexCam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public Comment saveComment(CommentRequest request) {
        User userExist = userRepository.findByEmail(request.getEmail());
        LocalDateTime now = LocalDateTime.now();

        if (userExist != null) {
            Comment comment = Comment.builder()
                    .stars(request.getStars())
                    .comment(request.getComment())
                    .user(userExist)
                    .createdAt(now)
                    .build();

            commentRepository.save(comment);
            return comment;
        } else {
            return null;
        }
    }

    public void editComment(Long commentId, CommentRequest request) {
        User userExist = userRepository.findByEmail(request.getEmail());

        if (userExist != null) {
            Optional<Comment> existingCommentOpt = commentRepository.findById(commentId);

            if (existingCommentOpt.isPresent()) {
                Comment existingComment = existingCommentOpt.get();


                if (existingComment.getUser().getId_user() == userExist.getId_user()) {
                    existingComment.setStars(request.getStars());
                    existingComment.setComment(request.getComment());
                    existingComment.setCreatedAt(LocalDateTime.now());

                    commentRepository.save(existingComment);
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

        Optional<Comment> existingComment = commentRepository.findById(commentId);

        User user = userRepository.findByEmail(request.getEmail());


        if (!existingComment.isPresent()) {
            throw new NoSuchElementException("Comentario no encontrado");
        }

        if (user == null) {
            throw new NoSuchElementException("Usuario no encontrado");
        }

        if (existingComment.get().getUser().getId_user() != user.getId_user()) {
            throw new IllegalStateException("No puedes eliminar este comentario");
        }

        commentRepository.delete(existingComment.get());
    }



}
