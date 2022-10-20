package com.alkemy.ong.domain.users;

import com.alkemy.ong.domain.emails.MailGateway;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    private final UserGateway gateway;

    private MailGateway mailGateway;

    public UserService(UserGateway gateway, MailGateway mailGateway) {
        this.mailGateway = mailGateway;
        this.gateway = gateway;
    }

    public List<User> findAll() {
        return gateway.findAll();
    }

    public void deleteById(Long id) {
        gateway.deleteById(id);
    }

    public User save(User user) {
        User userSaved = gateway.save(user);
        mailGateway.sendMail(user.getEmail(), "Bienvenido", "Gracias por registrarte en nuestro sitio");
        return userSaved;
    }

    public User updateById(Long id, User user) {
        return gateway.updateById(id, user);
    }

    public User findByEmail(String email) {
        return gateway.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = gateway.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User retrievedUser) {
        String role = retrievedUser.getRoleId().toString();
        return List.of(new SimpleGrantedAuthority("ROLE_"+role));
    }

    public User findById(Long id) {
        return gateway.findById(id);
    }
}
