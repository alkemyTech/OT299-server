package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.OrganizationEntity;
import com.alkemy.ong.data.repositories.OrganizationRepository;
import com.alkemy.ong.domain.organizations.OrganizationGateway;
import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class DefaultOrganizationGateway implements OrganizationGateway {

    private final OrganizationRepository organizationRepository;

    @Override
    public Organization findById(Long id) {
        Organization organization = toModel(organizationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Organization", "id", id )));
        return organization;
    }

    private Organization toModel(OrganizationEntity organizationEntity){

        return Organization.builder().id(organizationEntity.getId())
                .name(organizationEntity.getName())
                .image(organizationEntity.getImage())
                .address(organizationEntity.getAddress())
                .phone(organizationEntity.getPhone())
                .email(organizationEntity.getEmail())
                .welcomeText(organizationEntity.getWelcomeText())
                .aboutUsText(organizationEntity.getAboutUsText())
                .updatedAt(organizationEntity.getUpdatedAt())
                .updatedAt(organizationEntity.getCreatedAt())
                .deleted(organizationEntity.isDeleted()).build();
    }
}
