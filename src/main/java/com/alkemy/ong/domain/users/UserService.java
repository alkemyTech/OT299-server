package com.alkemy.ong.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserGateway gateway;

    public List<User> findAll() {
        return gateway.findAll();
    }
}
