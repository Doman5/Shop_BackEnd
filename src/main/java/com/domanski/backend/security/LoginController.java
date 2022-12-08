package com.domanski.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.domanski.backend.security.model.ShopUserDetails;
import com.domanski.backend.security.model.User;
import com.domanski.backend.security.model.UserRole;
import com.domanski.backend.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final long expirationTime;
    private final String secret;
    private final UserRepository userRepository;

    public LoginController(AuthenticationManager authenticationManager,
                           @Value("${jwt.expirationTime}") long expirationTime,
                           @Value("${jwt.secret}") String secret, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.expirationTime = expirationTime;
        this.secret = secret;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginCredentials loginCredentials ) {
        return authenticate(loginCredentials.username, loginCredentials.password);
    }

    @PostMapping("/register")
    public Token register(@RequestBody @Valid RegisterCredentials registerCredentials) {
        if(!registerCredentials.password.equals(registerCredentials.repeatPassword)) {
            throw new IllegalArgumentException("Hasła nie są identyczne");
        }
        if(userRepository.existsByUsername(registerCredentials.username)) {
            throw new IllegalArgumentException("Taki użytkownika już istnieje w bazie");
        }
        userRepository.save(User.builder()
                        .username(registerCredentials.username)
                        .password("{bcrypt}" + new BCryptPasswordEncoder().encode(registerCredentials.password))
                        .enabled(true)
                        .authorities(List.of(UserRole.ROLE_CUSTOMER))
                        .build());
                return authenticate(registerCredentials.username, registerCredentials.password);
    }

    private Token authenticate(String username, String password) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        ShopUserDetails principal = (ShopUserDetails) authenticate.getPrincipal();
        String token = JWT.create()
                .withSubject(String.valueOf(principal.getId()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
        return new Token(token, principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(s -> UserRole.ROLE_ADMIN.name().equals(s))
                .map(s -> true)
                .findFirst()
                .orElse(false));

    }

    @Getter
    private static class LoginCredentials {

        private String username;
        private String password;
    }

    @Getter
    private static class RegisterCredentials {
        @Email
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String repeatPassword;
    }

    @Getter
    @AllArgsConstructor
    private static class Token {
        private String token;
        private boolean adminAccess;
    }
}
