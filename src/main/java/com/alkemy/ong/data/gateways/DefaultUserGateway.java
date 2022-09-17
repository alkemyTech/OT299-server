package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.UserEntity;
import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultUserGateway {
    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll().stream()
                .map(this::tomodel)
                .collect(Collectors.toList());
    }

    private User tomodel(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getPhoto(),
                entity.getRoleId(),
                entity.getUpdatedAt(),
                entity.getCreatedAt(),
                entity.isDeleted());
    }
}
