package com.alkemy.ong.domain.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserService {
    UserGateway gateway;

    public List<User> findAll() {
        return gateway.findAll();
    }
}
