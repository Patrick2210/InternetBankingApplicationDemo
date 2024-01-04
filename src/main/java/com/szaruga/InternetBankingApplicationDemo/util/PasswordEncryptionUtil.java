package com.szaruga.InternetBankingApplicationDemo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptionUtil {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void encrypt(String password) {
        passwordEncoder.encode(password);
    }
    //todo zrobic tutaj kodowanko
}
