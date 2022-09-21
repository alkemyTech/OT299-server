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

    MemberService memberService;
    @GetMapping()
    public List<MemberDto> findAll() {

        return  memberService.findAll().stream()
                .map(this::toDto)
                .collect(toList());
    }
    private MemberDto toDto (Member member){
        return  MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .facebookUrl(member.getFacebookUrl())
                .instagramUrl(member.getInstagramUrl())
                .linkedinUrl(member.getLinkedinUrl())
                .image(member.getImage())
                .createdAt(member.getCreatedAt())
                .updateAt(member.getUpdatedAt())
                .deleted(member.isDeleted())
                .build();
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
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