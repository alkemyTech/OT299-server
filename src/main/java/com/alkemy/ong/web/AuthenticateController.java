package com.alkemy.ong.web;

import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final UserService service;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{errors: [Invalid Input]}")})}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{errors: [Invalid Input]}")})}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "error: User not found with: id :")})}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{error: [Internal Server Error]}")})})
    })
    public ResponseEntity<?> authenticate(@Valid @RequestBody UserAuthDTO userAuthDTO) throws Exception {
        Map<String, Object> response = new HashMap<>();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDTO.email, userAuthDTO.password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDTO user = toDTO(service.findByEmail(userAuthDTO.getEmail()));
        response.put("User", user);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

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
    }

    private UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
