package com.alkemy.ong.domain.members;

import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private boolean deleted;

    public Member(){

    }

    public Member(Long id, String name, String facebookUrl, String instagramUrl, String linkedinUrl, String image, String description, LocalDateTime createdAt, LocalDateTime updateAt, boolean deleted) {
        this.id = id;
        this.name = name;
        this.facebookUrl = facebookUrl;
        this.instagramUrl = instagramUrl;
        this.linkedinUrl = linkedinUrl;
        this.image = image;
        this.description = description;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
