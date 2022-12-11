package com.domanski.backend.security.service;

import com.domanski.backend.common.mail.EmailClientService;
import com.domanski.backend.security.model.User;
import com.domanski.backend.security.model.dto.ChangePassword;
import com.domanski.backend.security.model.dto.EmailObject;
import com.domanski.backend.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LostPasswordService {

    private final UserRepository userRepository;
    private final EmailClientService emailClientService;

    @Value("${app.serviceAddress}")
    private String serviceAddress;

    @Transactional
    public void sendLostPasswordEmail(EmailObject email) {
        User user = userRepository.findByUsername(email.getEmail())
                .orElseThrow(() -> new RuntimeException("Taki email nie istnieje"));
        String hash = generateHash(user);
        user.setHash(hash);
        user.setHashDate(LocalDateTime.now());
        emailClientService.getInstance().send(
                email.getEmail(), "Zresetuj hasło" , createMessage(createLink(hash))
        );
    }

    private String createMessage(String link) {
        return "Wygenerowaliśmy dla ciebie link do zmiany hasła" +
                "\n\n Kliknij link, żeby zresetować hasło: " +
                "\n" + link +
                "\n\n Dziękujemy";
    }

    private String createLink(String hash) {
        return serviceAddress + "/lostPassword/" + hash;
    }

    private String generateHash(User user) {
        String toHash = user.getId() + user.getUsername() + user.getPassword() + LocalDateTime.now();
        return DigestUtils.sha256Hex(toHash);
    }

    @Transactional
    public void changePassword(ChangePassword changePassword) {
        if(!Objects.equals(changePassword.getPassword(),changePassword.getRepeatPassword())) {
            throw new RuntimeException("Hasła nie są identyczne");
        }
        User user = userRepository.findByHash(changePassword.getHash())
                .orElseThrow(() -> new RuntimeException("Nieprawidłowy link"));

        if(user.getHashDate().plusMinutes(10).isAfter(LocalDateTime.now())) {
            user.setPassword("{bcryp}" + new BCryptPasswordEncoder().encode(changePassword.getPassword()));
            user.setHash(null);
            user.setHashDate(null);
        } else {
            throw new RuntimeException("Link stracił ważność");
        }
    }
}
