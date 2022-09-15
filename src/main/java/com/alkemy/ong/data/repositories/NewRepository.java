package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.NewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewRepository extends CrudRepository<NewEntity, Long> {

    List<NewEntity> findAll();

}
