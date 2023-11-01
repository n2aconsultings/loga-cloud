package com.loga.authenticationservice.vendor.config;

import com.loga.authenticationservice.entity.AuthSession;
import com.loga.authenticationservice.repository.AuthSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Authentication implements UserDetailsService {

    @Autowired
    private AuthSessionRepository authSessionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthSession> userDetails = authSessionRepository.findByToken(username);
        return userDetails.map(AuthSession::new)
                .orElseThrow(()->new UsernameNotFoundException("User cannot been authenticated !!!"));
    }
}
