package com.alkemy.ong.web;

import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationService;
import com.alkemy.ong.domain.slides.Slide;
import com.alkemy.ong.domain.slides.SlideService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/organization/public")
@Builder
public class OrganizationsController {

    private final OrganizationService organizationService;

    private final SlideService slideService;

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> findOrganizationById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(toDto(organizationService.findOrganizationById(id)));
    }

    @PreAuthorize("hasRole('ROLE_1')")
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDto>update (@Valid @RequestBody OrganizationDto organizationDto,
                                                             @PathVariable final Long id){
        Organization organization = organizationService.updateOrganization(id, toModel(organizationDto));
        return new ResponseEntity<>(toDto(organization), HttpStatus.OK );
    }

    private OrganizationDto toDto(Organization organization){

        return OrganizationDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .image(organization.getImage())
                .address(organization.getAddress())
                .phone(organization.getPhone())
                .facebook(organization.getFacebook())
                .linkedin(organization.getLinkedin())
                .instagram(organization.getInstagram())
                .slides(slideService.findByOrganizationId(organization.getId()).stream().map(this::slideDto).collect(Collectors.toList()))
                .build();
    }

    private Organization toModel(OrganizationDto organizationDto){
        return Organization.builder()
                .name(organizationDto.getName())
                .image(organizationDto.getImage())
                .address(organizationDto.getAddress())
                .phone(organizationDto.getPhone())
                .email(organizationDto.getEmail())
                .welcomeText(organizationDto.getWelcomeText())
                .aboutUsText(organizationDto.getAboutUsText())
                .facebook(organizationDto.getFacebook())
                .linkedin(organizationDto.getLinkedin())
                .instagram(organizationDto.getInstagram())
                .build();
    }

    private SlideController.SlideDto slideDto(Slide slide){
        return SlideController.SlideDto.builder()
                .id(slide.getId())
                .imageUrl(slide.getImageUrl())
                .slideText(slide.getSlideText())
                .slideOrder(slide.getSlideOrder())
                .organizationId(slide.getOrganizationId())
                .build();
    }

    @Setter
    @Getter
    @Builder
    public static class OrganizationDto {

        private Long id;

        @NotEmpty(message = "The field name cannot be null or empty.")
        @Pattern(regexp="^[\\p{L} .'-]+$",message = "Invalid Input for field name.")
        private String name;

        private String image;
        private String address;

        @Min(value = 1, message = "The field phone cannot be null and only receive numbers.")
        private int phone;

        @NotEmpty(message = "The field email cannot be null or empty.")
        private String email;

        @NotEmpty(message = "The field welcomeText cannot be null or empty.")
        private String welcomeText;

        private String aboutUsText;
        private String facebook;
        private String linkedin;
        private String instagram;

        private List<SlideController.SlideDto> slides;
    }

}
