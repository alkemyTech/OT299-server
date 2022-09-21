package com.alkemy.ong.domain.comments;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Comment {

    private Long id;
    private long userId;
    private String body;
    private long newsId;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private boolean deleted = false;
}
