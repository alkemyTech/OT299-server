package com.alkemy.ong.domain.organizations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationGateway organizationGateway;

    public Organization findOrganizationById(Long id){
         return organizationGateway.findById(id);
    }

    public Organization updateOrganization(Long id, Organization organization) {
        return organizationGateway.updateOrganization(id, organization);
    }

}
