package com.alkemy.ong.domain.categories;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Categories {
    private long id;
    private String name;
    private String description;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted = false;
}
