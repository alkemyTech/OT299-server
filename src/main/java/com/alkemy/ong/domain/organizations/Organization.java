package com.alkemy.ong.domain.organizations;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Organization {

    private Long id;

    private String name;

    private String image;

    private String address;

    private int phone;

    private String email;

    private String welcomeText;

    private String aboutUsText;

    private String facebook;

    private String linkedin;

    private String instagram;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    private boolean deleted = false;
}
