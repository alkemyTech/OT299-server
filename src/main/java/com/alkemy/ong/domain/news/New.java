package com.alkemy.ong.domain.news;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class New {

    private Long id;

    private String name;

    private String content;

    private String image;

    private Long categoryId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean deleted;

    public void addAttribute(String list, List<New> content) {
    }
}
