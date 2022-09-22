package com.alkemy.ong.domain.testimonials;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Testimonial {

    private Long id;
    private String name;
    private String content;
    private String image;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private boolean deleted = false;

}
