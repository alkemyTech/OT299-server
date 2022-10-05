package com.alkemy.ong.web;

import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticateController {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final UserService service;

   @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody UserAuthDTO userAuthDTO) throws Exception {
        Map<String, Object> response = new HashMap<>();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDTO.email, userAuthDTO.password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDTO user = toDTO(service.findByEmail(userAuthDTO.getEmail()));
        response.put("User", user);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User userRegister) {
       //encriptar
        String passwordEncryp = encoder.encode(userRegister.getPassword());
        userRegister.setPassword(passwordEncryp);
        UserDTO user = toDTO(service.save(userRegister));
        return new ResponseEntity<>(userRegister, HttpStatus.CREATED);
    }

    private UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleId(user.getRoleId())
                .build();
    }

    // ------------ DTOs

    @Setter
    @Getter
    @Builder
    public static class UserAuthDTO {
        @NotNull
        private String email;
        @NotNull
        private String password;
    }

    @Setter
    @Getter
    @Builder
    public static class UserDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private Long roleId;
    }

    @Setter
    @Getter
    @Builder
    public static class UserRegisterDTO {
        @NotNull
        private String firstName;
        @NotNull
        private String lastName;
        @NotNull
        private String email;
        @NotNull
        private String password;
    }

}
