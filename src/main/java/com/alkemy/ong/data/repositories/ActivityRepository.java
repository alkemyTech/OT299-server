package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.ActivityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<ActivityEntity, Long> {
    List<ActivityEntity> findAll();
}
