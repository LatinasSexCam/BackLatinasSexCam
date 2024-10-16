package com.LatinasSexCam.infrastructure.dto.response;

import com.LatinasSexCam.domain.model.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommentResponseDTO {

    private Long idComment;
    private String comment;
    private int stars;
    private LocalDateTime createdAt;
    private String userName;

    public CommentResponseDTO(Comment comment) {
        this.idComment = comment.getIdComment();
        this.comment = comment.getComment();
        this.stars = comment.getStars();
        this.createdAt = comment.getCreatedAt();
        this.userName = (comment.getUser() != null) ? comment.getUser().getUser_name() : "Unknown";  // Manejo de nulos
    }

}
