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

    public User save(User user) {
        return gateway.save(user);
    }

    public User updateById(Long id, User user) {
        return gateway.updateById(id, user);
    }

    public String encryptPassword(String password) {
        return gateway.encryptPassword(password);
    }

    public String authentication(String email, String password) throws Exception {
        return gateway.authentication(email, password);
    }
}
