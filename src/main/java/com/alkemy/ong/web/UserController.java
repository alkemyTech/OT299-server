package com.alkemy.ong.web;

import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll().stream().map(this::toDTO).collect(toList()));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity deleteUser(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .roleId(user.getRoleId())
                .build();
    }

    @Setter
    @Getter
    @Builder
    public static class UserDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String photo;
        private Long roleId;
    }


}
