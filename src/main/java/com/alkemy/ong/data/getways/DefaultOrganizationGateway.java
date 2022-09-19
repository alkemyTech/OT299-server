package com.alkemy.ong.data.getways;

import com.alkemy.ong.data.entities.OrganizationEntity;
import com.alkemy.ong.data.repositories.OrganizationRepository;
import com.alkemy.ong.domain.organizations.OrganizationGateway;
import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultOrganizationGateway implements OrganizationGateway {

    private final OrganizationRepository organizationRepository;

    @Override
    public Organization findById(Long id) {
        Organization organization = toModel(organizationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Organization", "id", id )));
        return organization;
    }

    private Organization toModel(OrganizationEntity organizationEntity){

        return new Organization(organizationEntity.getId(),
                organizationEntity.getName(),
                organizationEntity.getImage(),
                organizationEntity.getAddress(),
                organizationEntity.getPhone(),
                organizationEntity.getEmail(),
                organizationEntity.getWelcomeText(),
                organizationEntity.getAboutUsText(),
                organizationEntity.getUpdatedAt(),
                organizationEntity.getCreatedAt(),
                organizationEntity.isDeleted()
        );
    }
}
