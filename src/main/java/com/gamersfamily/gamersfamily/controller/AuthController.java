package com.gamersfamily.gamersfamily.controller;

import com.gamersfamily.gamersfamily.dto.JWTAuthResponse;
import com.gamersfamily.gamersfamily.dto.LoginDto;
import com.gamersfamily.gamersfamily.dto.SignUpDto;
import com.gamersfamily.gamersfamily.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Auth Controller exposes signin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "REST API to register or Signup user to Blog application")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticationUser(@Valid @RequestBody LoginDto loginDto){
        logger.info("Sign in User");
        return userService.signInUser(loginDto);
    }

    @ApiOperation(value = "REST API to Signin or Login user to Blog application")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){
        logger.info("Registering User");
        return userService.registerUser(signUpDto);
    }
}
