package com.alkemy.ong.web;

import com.alkemy.ong.domain.news.New;
import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserService;
import com.alkemy.ong.web.security.JwtUtil;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    private final JwtUtil jwtUtil;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll().stream().map(this::toDTO).collect(toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateById(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity(toDTO(service.updateById(id, toModel(userDTO))), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        return new ResponseEntity(toDTO(service.save(toModel(userDTO))), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById (@PathVariable Long id,@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        String email = jwtUtil.getUsernameFromToken(token);
        User user = service.findByEmail(email);
        if(user.getId() == id){
            return ResponseEntity.ok(service.findById(id));
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    private UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .photo(user.getPhoto())
                .roleId(user.getRoleId())
                .build();
    }

    private User toModel(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .photo(userDTO.getPhoto())
                .roleId(userDTO.getRoleId())
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
        private String password;
        private String photo;
        private Long roleId;
    }

}
