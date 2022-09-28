package com.alkemy.ong.domain.users;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserGateway {
    List<User> findAll();
    void deleteById(Long id);
    User updateById(Long Id, User user);
    User getUserByEmail(String email);
    String authentication(String email, String password);
}
