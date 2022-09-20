package com.alkemy.ong.web;

import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization/public")
@Builder
public class OrganizationsController {

    private final OrganizationService organizationService;

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> findOrganizationById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(toDto(organizationService.findOrganizationById(id)));
    }


    private OrganizationDto toDto(Organization organization){

        return new OrganizationDto(organization.getName(),
                organization.getImage(),
                organization.getAddress(),
                organization.getPhone()
        );
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrganizationDto {

        private String name;
        private String image;
        private String address;
        private int phone;
    }

}
