package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.SlideEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlideRepository extends CrudRepository<SlideEntity, Long> {

    List<SlideEntity> findAll();

    void deleteById(Long id);

    List<SlideEntity> findAllByOrganizationId(Long organizationId);
}
