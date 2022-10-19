package com.alkemy.ong.web.security;

import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private final UserService service;

    @Value("${jwt.secret}")
    private String secretKey;

    public JwtUtil(UserService service) {
        this.service = service;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        User user = service.findByEmail(userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", userDetails.getAuthorities().toString());
        claims.put("id", user.getId().toString());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean checkIfIsSameUser(String token, int userID ){
        Claims claims = getAllClaimsFromToken(token);
        String role = claims.get("ROLE").toString();
        int tokenId = Integer.parseInt(claims.get("id").toString()) ;

        if(role.equals("[ROLE_1]") || tokenId==userID) {

            return true;
        }
        return false;
    }

}
