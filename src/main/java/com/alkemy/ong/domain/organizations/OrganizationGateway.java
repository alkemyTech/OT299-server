package com.alkemy.ong.domain.organizations;


public interface OrganizationGateway {

   Organization findById(Long id);

   Organization updateOrganization(Long id, Organization organization);

}
