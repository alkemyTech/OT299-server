package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.OrganizationEntity;
import com.alkemy.ong.data.repositories.OrganizationRepository;
import com.alkemy.ong.domain.organizations.OrganizationGateway;
import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
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

    @Override
    public Organization updateOrganization(Long id, Organization organization) {
        OrganizationEntity organizationEntity = organizationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Organization", "id", id));
        return toModel(updateOrganizationMapper(organization, organizationEntity));

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
                .facebook(organizationEntity.getFacebook())
                .linkedin(organizationEntity.getLinkedin())
                .instagram(organizationEntity.getInstagram())
                .updatedAt(organizationEntity.getUpdatedAt())
                .updatedAt(organizationEntity.getCreatedAt())
                .deleted(organizationEntity.isDeleted()).build();
    }

    private OrganizationEntity updateOrganizationMapper(Organization organization, OrganizationEntity organizationEntity){
        organizationEntity.setName(organization.getName());
        organizationEntity.setImage(organization.getImage());
        organizationEntity.setAddress(organization.getAddress());
        organizationEntity.setPhone(organization.getPhone());
        organizationEntity.setEmail(organization.getEmail());
        organizationEntity.setWelcomeText(organization.getWelcomeText());
        organizationEntity.setAboutUsText(organization.getAboutUsText());
        organizationEntity.setFacebook(organization.getFacebook());
        organizationEntity.setLinkedin(organization.getLinkedin());
        organizationEntity.setInstagram(organization.getInstagram());
        return organizationRepository.save(organizationEntity);
    }
}
