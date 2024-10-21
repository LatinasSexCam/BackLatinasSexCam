package com.LatinasSexCam.infrastructure.adapters;

import com.LatinasSexCam.domain.model.Comment;
import com.LatinasSexCam.domain.ports.CommentRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.CommentEntity;
import com.LatinasSexCam.infrastructure.repository.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentRepositoryAdapter implements CommentRepositoryPort {

    private final CommentJpaRepository commentJpaRepository;
    private final UserRepositoryAdapter userRepositoryAdapter;
    @Override
    public Optional<Comment> findById(Long id) {
        return commentJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(toEntity(comment));
    }

    @Override
    public void delete(Comment comment) {
        commentJpaRepository.delete(toEntity(comment));
    }

    @Override
    public List<Comment> findAllComments() {
        List<CommentEntity> commentEntities = commentJpaRepository.findAll();
        return commentEntities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private Comment toDomain(CommentEntity entity){
        return Comment.builder()
                .idComment(entity.getIdComment())
                .comment(entity.getComment())
                .stars(entity.getStars())
                .createdAt(entity.getCreatedAt())
                .user(userRepositoryAdapter.toDomain(entity.getUser()))
                .build();
    }

    private CommentEntity toEntity(Comment comment){
        return CommentEntity.builder()
                .idComment(comment.getIdComment())
                .comment(comment.getComment())
                .stars(comment.getStars())
                .createdAt(comment.getCreatedAt())
                .user(userRepositoryAdapter.toEntity(comment.getUser()))
                .build();
    }
}
