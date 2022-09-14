package com.alkemy.ong.data.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository {
    List<UserEntity> findAll();
}
