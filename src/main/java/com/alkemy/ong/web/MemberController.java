package com.alkemy.ong.web;


import com.alkemy.ong.domain.members.DefaultMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    DefaultMemberService defaultMemberService;

    @GetMapping()
    public List<MemberDto> findMember() {
        //return defaultMemberService.findMember();

        return  defaultMemberService.findMember().stream().map(x -> new MemberDto(x.getId(),
                x.getName(),
                x.getFacebookUrl(),
                x.getInstagramUrl(),
                x.getLinkedinUrl(),
                x.getImage(),
                x.getDescription(),
                x.getCreatedAt(),
                x.getUpdateAt(),
                x.isDeleted()))
                .collect(Collectors.toList());



    }


    public static class MemberDto {
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

        public MemberDto(){

        }

        public MemberDto(Long id, String name, String facebookUrl, String instagramUrl, String linkedinUrl, String image, String description, LocalDateTime createdAt, LocalDateTime updateAt, boolean deleted) {
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

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFacebookUrl() {
            return facebookUrl;
        }

        public void setFacebookUrl(String facebookUrl) {
            this.facebookUrl = facebookUrl;
        }

        public String getInstagramUrl() {
            return instagramUrl;
        }

        public void setInstagramUrl(String instagramUrl) {
            this.instagramUrl = instagramUrl;
        }

        public String getLinkedinUrl() {
            return linkedinUrl;
        }

        public void setLinkedinUrl(String linkedinUrl) {
            this.linkedinUrl = linkedinUrl;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(LocalDateTime updateAt) {
            this.updateAt = updateAt;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

    }
}