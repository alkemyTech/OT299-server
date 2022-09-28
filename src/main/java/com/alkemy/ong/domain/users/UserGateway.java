package com.alkemy.ong.domain.users;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserGateway {
    List<User> findAll();

    void deleteById(Long id);

    User updateById(Long Id, User user);


    User save(User user);

    String encryptPassword(String password);

    boolean verifyPassword(String originalPassword, String hashPassword);

    String authentication(String email, String password) throws Exception;
}
