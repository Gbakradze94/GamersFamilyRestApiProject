package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.dto.JWTAuthResponse;
import com.gamersfamily.gamersfamily.dto.LoginDto;
import com.gamersfamily.gamersfamily.dto.SignUpDto;
import com.gamersfamily.gamersfamily.model.Role;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.RoleRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import com.gamersfamily.gamersfamily.security.JwtTokenProvider;
import com.gamersfamily.gamersfamily.service.SettingService;
import com.gamersfamily.gamersfamily.service.UserService;
import com.gamersfamily.gamersfamily.utils.mail.EmailSettingBag;
import com.gamersfamily.gamersfamily.utils.mail.SettingUtility;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final SettingService settingService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository, AuthenticationManager authenticationManager,
                           JwtTokenProvider tokenProvider, SettingService settingService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.settingService = settingService;
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpDto signUpDto) {
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }

        String verificationCode = RandomString.make(64);

        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setVerificationcode(verificationCode);
        user.setEnabled(false);

        if(roleRepository.findByName("ROLE_USER").isEmpty()){
            return new ResponseEntity<>("Role_User Does not Exist in Database", HttpStatus.BAD_REQUEST);
        }

        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);

        return new ResponseEntity<>("User Successfully Registered", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<JWTAuthResponse> signInUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

//        User user = userRepository.findByEmail(loginDto.getEmail()).get();
//        if(user.enabled == true){
//
//        }
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationcode(verificationCode);

        if (user == null   || user.isEnabled()) {
            return false;
        } else {
            userRepository.enable(user.getId());
            return true;
        }
    }

    public void sendVerificationEmail(HttpServletRequest request, SignUpDto user) throws MessagingException, UnsupportedEncodingException {
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
        log.info(content);
        String verifyURL = SettingUtility.getSiteURL(request) + "/api/v1/auth/verify?code=" + user.getVerificationcode();

        log.info("VERIFY URL value: " + verifyURL);
        content = content.replace("[[URL]]",verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }
}
