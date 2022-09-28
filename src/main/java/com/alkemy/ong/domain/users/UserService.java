package com.alkemy.ong.domain.users;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserGateway gateway;

    public UserService(UserGateway gateway) {
        this.gateway = gateway;
    }

    public List<User> findAll() {
        return gateway.findAll();
    }

    public void deleteById(Long id) {
        gateway.deleteById(id);
    }

    public User updateById(Long id, User user) {
        return gateway.updateById(id, user);
    }

    public String authentication(String email, String password) {
        return gateway.authentication(email, password);
    }
}
