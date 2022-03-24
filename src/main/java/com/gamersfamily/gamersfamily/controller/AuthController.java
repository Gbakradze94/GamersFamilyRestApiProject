package com.gamersfamily.gamersfamily.controller;

import com.gamersfamily.gamersfamily.dto.JWTAuthResponse;
import com.gamersfamily.gamersfamily.dto.LoginDto;
import com.gamersfamily.gamersfamily.dto.SignUpDto;
import com.gamersfamily.gamersfamily.service.SettingService;
import com.gamersfamily.gamersfamily.service.UserService;
import com.gamersfamily.gamersfamily.utils.mail.EmailSettingBag;
import com.gamersfamily.gamersfamily.utils.mail.SettingUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Api(value = "Auth Controller exposes signin and signup REST APIs")
@Slf4j
@RestController
@RequestMapping(AuthController.BASE_URL)
public class AuthController {

    public static final String BASE_URL = "/api/v1/auth";

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

<<<<<<< HEAD
    @ApiOperation(value = "REST API to Sign user In to Blog application")
=======
    @Autowired
    private SettingService settingService;

    @ApiOperation(value = "REST API to register or Signup user to Blog application")
>>>>>>> confirm_registration
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticationUser(@Valid @RequestBody LoginDto loginDto) {
        logger.info("Sign in User");
        return userService.signInUser(loginDto);
    }

    @ApiOperation(value = "REST API to Register user to Blog application")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        sendVerificationEmail(request, signUpDto);
        logger.info("Registering User");
        return userService.registerUser(signUpDto);
    }

    private void sendVerificationEmail(HttpServletRequest request, SignUpDto user) throws MessagingException, UnsupportedEncodingException {
        EmailSettingBag emailSettings = settingService.getEmailSettings();
        JavaMailSender mailSender = SettingUtility.prepareMailSender(emailSettings);

        String toAddress = user.getEmail();
        String subject = emailSettings.getUserVerifySubject();
        String content = emailSettings.getCustomerVerifyContent();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(emailSettings.getFromAddress(),emailSettings.getSenderName());
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        logger.info(content);
        String verifyURL = SettingUtility.getSiteURL(request) + "/api/v1/auth/verify?code=" + user.getVerificationcode();

        logger.info("VERIFY URL value: " + verifyURL);
        content = content.replace("[[URL]]",verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/verify")
    public ResponseEntity<HttpStatus> verifyAccount(@Param("code") String code) {

        boolean verified = userService.verify(code);
        if (verified == true) {
            log.info("VERIFIED");
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            log.info("NOT VERIFIED");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
