package com.alkemy.ong.web;

import com.alkemy.ong.domain.members.Member;
import com.alkemy.ong.domain.members.MemberService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<MemberDto> create(@Valid @RequestBody MemberDto memberDto){
        Member member = memberService.create(toModel(memberDto));
        return new ResponseEntity<>(toDto(member),HttpStatus.CREATED);

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

    public Member toModel(MemberDto memberDto){
        return Member.builder()
                .id(memberDto.getId())
                .name(memberDto.getName())
                .facebookUrl(memberDto.getFacebookUrl())
                .instagramUrl(memberDto.getInstagramUrl())
                .linkedinUrl(memberDto.getLinkedinUrl())
                .image(memberDto.getImage())
                .createdAt(memberDto.getCreatedAt())
                .updatedAt(memberDto.getUpdateAt())
                .deleted(memberDto.isDeleted())
                .build();
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberDto {
        private Long id;
        @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
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