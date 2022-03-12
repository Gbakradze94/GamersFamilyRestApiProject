package com.gamersfamily.gamersfamily.utils.other;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {
    public static void main(String[] args){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123"));

//        $2a$10$bUoJBxkgCnc7qbRVfEwr7ec/Qjwyc8jq6JmbgN9GxDjvZD.zvnRJy
    }
}
