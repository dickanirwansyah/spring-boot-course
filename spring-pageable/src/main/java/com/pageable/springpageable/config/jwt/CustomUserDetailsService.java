package com.pageable.springpageable.config.jwt;

import com.pageable.springpageable.entity.User;
import com.pageable.springpageable.exception.MessageException;
import com.pageable.springpageable.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username with "+username+" not found"));

        log.info("USER LOGIN BY USERNAME  => "+user.toString());

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new MessageException("user with id "+userId+" not found"));

        log.info("USE FIND BY ID => "+user.getId());

        return UserPrincipal.create(user);
    }
}
