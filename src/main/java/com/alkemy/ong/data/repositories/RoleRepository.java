package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    List<RoleEntity> findAll();
}
