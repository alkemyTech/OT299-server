package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.ContactsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends CrudRepository<ContactsEntity, Long> {
    List<ContactsEntity> findAll();
}
