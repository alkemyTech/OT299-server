package com.alkemy.ong.web;

import com.alkemy.ong.domain.emails.Email;
import com.alkemy.ong.domain.emails.MailService;
import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserService;
import com.alkemy.ong.web.security.JwtUtil;
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
import org.springframework.security.core.userdetails.UserDetails;
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
    public static final String SUBJECT = "Registration ONG Somos Mas";
    public static final String CONTENT = "Thank you for registering";
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final UserService service;
    private final JwtUtil jwtUtil;
    private final MailService mailService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody UserAuthDTO userAuthDTO) throws Exception {
        Map<String, Object> response = new HashMap<>();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDTO.email, userAuthDTO.password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDTO user = toDTO(service.findByEmail(userAuthDTO.getEmail()));
        UserDetails userDetails = service.loadUserByUsername(user.email);
        String jwtToken = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new UserAuthResponseDTO(jwtToken), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User userRegister) {
        String passwordEncrypted = encoder.encode(userRegister.getPassword());
        userRegister.setPassword(passwordEncrypted);

        UserDTO user = toDTO(service.register(userRegister));
        mailService.sendMail(Email.builder().emailRecipient(user.getEmail()).subject(SUBJECT).content(CONTENT).build());

        return new ResponseEntity<>(user, HttpStatus.CREATED);

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

    @Getter
    @Setter
    @Builder
    public static class UserAuthResponseDTO {
        private String jwt;
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
