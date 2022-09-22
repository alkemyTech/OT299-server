package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.UserEntity;
import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class DefaultUserGateway implements UserGateway {
    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll().stream()
                .map(this::toModel)
                .collect(toList());
    }

    @Override
    public void updateById(Long id) {
    }

    private User toModel(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .photo(entity.getPhoto())
                .roleId(entity.getRoleId().getId())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .deleted(entity.isDeleted())
                .build();
    }
}
