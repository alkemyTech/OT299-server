package com.alkemy.ong.domain.slides;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Slide {
    private Long id;
    private String imageUrl;
    private String slideText;
    private Long slideOrder;
    private Long organizationId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;
}
