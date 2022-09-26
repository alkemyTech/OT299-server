package com.alkemy.ong.domain.users;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserGateway {
    List<User> findAll();

    User updateById(Long Id, User user);
}
