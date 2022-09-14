package com.alkemy.ong.data.organizations;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends CrudRepository<OrganizationEntity, Long> {

    List<OrganizationEntity> findAll();
}
