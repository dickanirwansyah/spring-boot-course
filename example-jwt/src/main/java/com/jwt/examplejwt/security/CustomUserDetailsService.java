package com.jwt.examplejwt.security;

import com.jwt.examplejwt.entity.Users;
import com.jwt.examplejwt.exception.ResourceNotFoundException;
import com.jwt.examplejwt.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("user not found with username or email "+ usernameOrEmail));

        return UserPrincipal.create(users);
    }

    @Transactional
    public UserDetails loadUsersById(Long id){
        Users users = usersRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", id));

        return UserPrincipal.create(users);
    }
}
