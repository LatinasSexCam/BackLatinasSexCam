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

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ResponseEntity<String> saveComment(CommentRequest request) {

        String responseMessage;
        HttpStatus status;
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
             responseMessage = "Comentario registrado con éxito";
             status = HttpStatus.CREATED;
         }else{
             responseMessage = "Usuario no encontrado";
             status = HttpStatus.NOT_FOUND;
         }
        return new ResponseEntity<>(responseMessage, status);
    }
}
