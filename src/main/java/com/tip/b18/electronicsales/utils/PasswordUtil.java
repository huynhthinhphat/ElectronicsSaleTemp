package com.tip.b18.electronicsales.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class PasswordUtil {
    private final PasswordEncoder passwordEncoder;

    public String encryptPassword(String password){
        return passwordEncoder.encode(password);
    }

    public boolean matches(String password, String encodedPassword){
        return passwordEncoder.matches(password, encodedPassword);
    }
}
