package com.tip.b18.electronicsales.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface PasswordService {
    String encryptPassword(String password);
    boolean matches(String password, String encodedPassword);
}
