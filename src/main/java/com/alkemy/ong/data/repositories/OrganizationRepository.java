package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends CrudRepository<OrganizationEntity, Long> {

    List<OrganizationEntity> findAll();
}
