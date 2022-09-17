package com.alkemy.ong.web;

import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    UserService service;

    @GetMapping()
    public List<UserDTO> findAll() {
        return service.findAll().stream().map(this::toDTO).collect(toList());
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoto(), user.getRoleId());
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String photo;
        private Long roleId;
    }


}
