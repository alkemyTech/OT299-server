package com.alkemy.ong.web;

import com.alkemy.ong.domain.users.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final UserService service;

    @PostMapping("/login")
    public String authentication(@Valid @RequestBody UserAuthDTO userAuthDTO) throws Exception {
        return service.authentication(userAuthDTO.getEmail(), userAuthDTO.getPassword());
    }

    @Setter
    @Getter
    @Builder
    public static class UserAuthDTO {
        private String email;
        private String password;
    }
}
