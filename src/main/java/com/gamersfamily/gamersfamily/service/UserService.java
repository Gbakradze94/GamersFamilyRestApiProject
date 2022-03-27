package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.JWTAuthResponse;
import com.gamersfamily.gamersfamily.dto.LoginDto;
import com.gamersfamily.gamersfamily.dto.SignUpDto;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface UserService {
    ResponseEntity<?> registerUser(SignUpDto signUpDto);
    ResponseEntity<JWTAuthResponse> signInUser(LoginDto loginDto);
    boolean verify(String verificationCode);
    void sendVerificationEmail(HttpServletRequest request, SignUpDto user) throws MessagingException, UnsupportedEncodingException;
}
