package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryPort {

    Optional<Comment> findById(Long id);
    void save(Comment comment);
    void delete(Comment comment);
    List<Comment>findAllComments();
}
