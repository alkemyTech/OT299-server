package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.RoleEntity;
import com.alkemy.ong.data.entities.UserEntity;
import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserGateway;
import lombok.Builder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        return toModel(repository.save(updateEntity(entity, user)));
    }

    @Override
    public User save(User user) {
        return toModel(repository.save(updateEntity(new UserEntity(), user)));
    }


    @Override
    public boolean authentication(String email, String password) throws Exception {
        UserEntity entity = repository.findByEmail(email);
        if (Objects.isNull(entity)) {
            throw new Exception();
        } else {
            return verifyPassword(password, entity.getPassword());
        }
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    private boolean verifyPassword(String originalPassword, String hashPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(originalPassword, hashPassword);
    }


    private User toModel(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(encryptPassword(entity.getPassword()))
                .photo(entity.getPhoto())
                .roleId(entity.getRoleId().getId())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .deleted(entity.isDeleted())
                .build();
    }


    private UserEntity updateEntity(UserEntity entity, User user) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(user.getRoleId());
        entity.setId(user.getId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPassword(encryptPassword(user.getPassword()));
        entity.setPhoto(user.getPhoto());
        entity.setRoleId(roleEntity);
        return entity;
    }
}
