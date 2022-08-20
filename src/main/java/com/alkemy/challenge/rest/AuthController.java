package com.alkemy.challenge.rest;

import com.alkemy.challenge.domain.User;
import com.alkemy.challenge.repository.UserRepository;
import com.alkemy.challenge.security.jwt.JwtTokenUtil;
import com.alkemy.challenge.security.payload.JwtResponse;
import com.alkemy.challenge.security.payload.LoginRequest;
import com.alkemy.challenge.security.payload.MessageResponse;
import com.alkemy.challenge.security.payload.RegisterRequest;
import com.alkemy.challenge.email.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmailServiceImpl emailService;

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authManager,
                          UserRepository userRepository,
                          PasswordEncoder encoder,
                          JwtTokenUtil jwtTokenUtil){
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) throws IOException, MessagingException {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Ya existe una cuenta con el nombre de usuario ingresado"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Ya existe una cuenta registrada con el correo ingresado"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        emailService.sendSimpleMessage(signUpRequest.getEmail());

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente"));
    }
}
