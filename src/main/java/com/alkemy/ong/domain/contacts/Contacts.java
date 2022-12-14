package com.alkemy.ong.domain.contacts;

import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class Contacts {
    private long id;
    private String name;
    private long phone;
    private String email;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted = false;
}
