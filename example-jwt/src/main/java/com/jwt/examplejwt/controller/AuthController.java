package com.jwt.examplejwt.controller;

import com.jwt.examplejwt.entity.Role;
import com.jwt.examplejwt.entity.RoleName;
import com.jwt.examplejwt.entity.Users;
import com.jwt.examplejwt.exception.AppException;
import com.jwt.examplejwt.payload.ApiResponse;
import com.jwt.examplejwt.payload.JsonWebTokensResponse;
import com.jwt.examplejwt.payload.LoginRequest;
import com.jwt.examplejwt.payload.SingupRequest;
import com.jwt.examplejwt.repository.RoleRepository;
import com.jwt.examplejwt.repository.UsersRepository;
import com.jwt.examplejwt.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jsonWebTokens = tokenProvider.generateToken(auth);
        return ResponseEntity.ok(new JsonWebTokensResponse(jsonWebTokens));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid SingupRequest singupRequest){

        /**check username is existing in database **/
        if (usersRepository.existsByEmail(singupRequest.getRequestUsername())){
            ApiResponse apiResponse = ApiResponse
                    .builder()
                    .timestamp(LocalDateTime.now())
                    .message("sorry username "+ singupRequest.getRequestUsername()+" is already taken !")
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        /** check email is existing in database **/
        if (usersRepository.existsByEmail(singupRequest.getRequestEmail())){
            ApiResponse apiResponse = ApiResponse
                    .builder()
                    .timestamp(LocalDateTime.now())
                    .message("sorry email "+singupRequest.getRequestEmail()+" is already taken !")
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        /** IF SUCCESS VALIDATION **/
        Users users = Users.builder()
                .name(singupRequest.getRequestName())
                .username(singupRequest.getRequestUsername())
                .email(singupRequest.getRequestEmail())
                .password(passwordEncoder.encode(singupRequest.getRequestPassword()))
                .build();

        /** ADD ROLE BY USER **/
        Role role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("Sorry internal server error because "+
                        RoleName.ROLE_USER+" not set"));

        users.setRoles(Collections.singleton(role));
        Users responseSave = usersRepository.save(users);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(responseSave.getUsername()).toUri();

        /** CALL BACK RESPONSE JSON **/
        ApiResponse apiResponse = ApiResponse
                .builder().message("success").timestamp(LocalDateTime.now()).success(true).build();

        return ResponseEntity.created(location).body(
                apiResponse);
    }

}
