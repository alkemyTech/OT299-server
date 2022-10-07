package com.alkemy.ong.web;

import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.members.Member;
import com.alkemy.ong.domain.members.MemberService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {

    MemberService memberService;

    @GetMapping()
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<OngPage<Member>> findAll(@RequestParam Integer page) {
        OngPage<Member> pageMembers = memberService.findAll(page);
        return  ResponseEntity.ok(pageMembers);
    }

    @DeleteMapping(path = "/{id}")
    @ApiResponse(responseCode = "200", description = "No Content")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity delete(@PathVariable Long id){
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<MemberDto> create(@Valid @RequestBody MemberDto memberDto){
        Member member = memberService.create(toModel(memberDto));
        return new ResponseEntity<>(toDto(member),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Ok")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<MemberDto>update (@Valid @RequestBody MemberDto memberDto,
                                            @PathVariable final Long id){
        Member member = memberService.update(toModel(memberDto), id);
        return new ResponseEntity<>(toDto(member), HttpStatus.OK );
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