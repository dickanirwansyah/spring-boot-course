package com.dicka.jjwtbackend.controller;

import com.dicka.jjwtbackend.entity.ERole;
import com.dicka.jjwtbackend.entity.Role;
import com.dicka.jjwtbackend.entity.User;
import com.dicka.jjwtbackend.payload.request.SignUpRequest;
import com.dicka.jjwtbackend.payload.response.MessageResponse;
import com.dicka.jjwtbackend.repository.RoleRepository;
import com.dicka.jjwtbackend.repository.UserRepository;
import com.dicka.jjwtbackend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class AuthController {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid SignUpRequest signUpRequest){

        if (userRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error : username is already taken."));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error : email is already taken."));
        }

        //create account

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null){

            Role role = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error : role is not found"));

            roles.add(role);
        }else{

            strRoles.forEach(role -> {
                switch (role){

                    case "admin" :
                        Role roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error : role is not found"));
                        roles.add(roleAdmin);
                        break;

                    case "mod" :
                        Role roleModerator = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error : role is not found"));
                        roles.add(roleModerator);
                        break;

                    default:
                        Role roleUser = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error : role is not found"));
                        roles.add(roleUser);
                }
            });

        }

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User register successfully"));
    }
}
