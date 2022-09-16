package com.alkemy.ong.web;

import com.alkemy.ong.domain.members.Member;
import com.alkemy.ong.domain.members.MemberService;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {
    MemberService defaultMemberService;
    @GetMapping()
    public List<MemberDto> findAll() {

        return  defaultMemberService.findAll().stream()
                .map(this::toDto)
                .collect(toList());
    }
    private MemberDto toDto (Member entity){
        return new MemberDto(entity.getId(),
                entity.getName(),
                entity.getFacebookUrl(),
                entity.getInstagramUrl(),
                entity.getLinkedinUrl(),
                entity.getImage(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isDeleted());
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
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
    }
}