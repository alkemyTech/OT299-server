package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.RoleEntity;
import com.alkemy.ong.data.entities.UserEntity;
import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserGateway;
import lombok.Builder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Component
@Builder
public class DefaultUserGateway implements UserGateway {
    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll().stream()
                .map(this::toModel)
                .collect(toList());
    }

    @Override
    public void deleteById(Long id) {
        UserEntity entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        repository.deleteById(entity.getId());
    }

    @Override
    public User updateById(Long id, User user) {
        UserEntity entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        return toModel(repository.save(toEntity(entity, user)));
    }

    @Override
    public String authentication(String email, String password) {
        UserEntity entity = repository.findByEmail(email);
        if (Objects.nonNull(entity)) {
        //TODO: comparar contraseña encriptada con contraseña enviada en petición, verificar que la contraseña sea válida
        }
        return "";
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

    private UserEntity toEntity(UserEntity entity, User user) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(user.getRoleId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPhoto(user.getPhoto());
        entity.setRoleId(roleEntity);
        return entity;
    }
}
