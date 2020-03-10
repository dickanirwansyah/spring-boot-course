package com.pageable.springpageable.controller;

import com.pageable.springpageable.config.jwt.JwtTokenProvider;
import com.pageable.springpageable.config.jwt.UserPrincipal;
import com.pageable.springpageable.entity.Role;
import com.pageable.springpageable.entity.RoleName;
import com.pageable.springpageable.entity.User;
import com.pageable.springpageable.exception.MessageException;
import com.pageable.springpageable.model.JsonWebTokensResponse;
import com.pageable.springpageable.model.LoginRequest;
import com.pageable.springpageable.model.RegisterRequest;
import com.pageable.springpageable.repo.RoleRepository;
import com.pageable.springpageable.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "login")
    public ResponseEntity<JsonWebTokensResponse> login(@RequestBody @Valid LoginRequest requestBody){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestBody.getUsername(), requestBody.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateToken(auth);
        UserPrincipal customUserPrincipal = (UserPrincipal) auth.getPrincipal();
        List<String> roles = customUserPrincipal.getAuthorities().stream()
                .map(role -> ((GrantedAuthority) role).getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JsonWebTokensResponse(jwtToken, customUserPrincipal.getId(),
                        customUserPrincipal.getUsername(), customUserPrincipal.getEmail(),
                        roles)
        );
    }

    @PostMapping(value = "register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest requestBody){

        if (userRepository.findByUsername(requestBody.getUsername()).isPresent()){
            return ResponseEntity.badRequest()
                    .body(new MessageException("username "+requestBody.getUsername()+" is already taken"));
        }

        if (userRepository.findByEmail(requestBody.getEmail()).isPresent()){
            return ResponseEntity.badRequest()
                    .body(new MessageException("email "+requestBody.getEmail()+" is already taken"));
        }

        if (userRepository.findByPhoneNumber(requestBody.getPhoneNumber()).isPresent()){
            return ResponseEntity.badRequest()
                    .body(new MessageException("phone number "+requestBody.getPhoneNumber()+" is already taken"));
        }

        Set<String> roles = requestBody.getRoles();
        Set<Role> getRoles = new HashSet<>();

        if (roles == null){
            Role roleDefault = roleRepository.findByName(RoleName.AGENT_STAFF)
                    .orElseThrow(() -> new MessageException("role staff not found"));
            getRoles.add(roleDefault);
        }else{

            roles.forEach(role -> {
                switch (role){

                    case "adminstrator" :
                        Role roleAdminstrator = roleRepository.findByName(RoleName.ADMINISTRATOR)
                                .orElseThrow(() -> new MessageException("role administrator not found"));
                        getRoles.add(roleAdminstrator);
                        break;
                    case "agentfinance" :
                        Role roleAgentFinance = roleRepository.findByName(RoleName.AGENT_FINANCE)
                                .orElseThrow(() -> new MessageException("role agent finance not found"));
                        getRoles.add(roleAgentFinance);
                        break;
                    case "agentmanager" :
                        Role roleAgentManager = roleRepository.findByName(RoleName.AGENT_MANAGER)
                                .orElseThrow(() -> new MessageException("role agent manager not found"));
                        getRoles.add(roleAgentManager);
                        break;
                    case "agentstaff" :
                        Role roleAgentStaff = roleRepository.findByName(RoleName.AGENT_STAFF)
                                .orElseThrow(() -> new MessageException("role agent staff not found"));
                        getRoles.add(roleAgentStaff);
                        break;
                    case "database" :
                        Role roleDatabase = roleRepository.findByName(RoleName.DATABASE)
                                .orElseThrow(() -> new MessageException("role database not found"));
                        getRoles.add(roleDatabase);
                        break;
                }
            });
        }

        User user = User.builder()
                .firstName(requestBody.getFirstName())
                .lastName(requestBody.getLastName())
                .dob(requestBody.getDob())
                .username(requestBody.getUsername())
                .phoneNumber(requestBody.getPhoneNumber())
                .password(passwordEncoder.encode(requestBody.getPassword()))
                .email(requestBody.getEmail())
                .roles(getRoles)
                .build();

        log.info("USER REGISTER => "+user.toString());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
