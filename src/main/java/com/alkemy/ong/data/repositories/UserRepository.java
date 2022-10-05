package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findAll();

    void deleteById(Long id);

    Optional<UserEntity> findByEmail(String email);
}
