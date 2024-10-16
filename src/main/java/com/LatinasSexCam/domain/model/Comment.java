package com.LatinasSexCam.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private Long idComment;
    private String comment;
    private int stars;
    private LocalDateTime createdAt;
    private User user;

}

