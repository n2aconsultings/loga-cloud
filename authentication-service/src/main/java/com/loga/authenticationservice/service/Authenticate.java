package com.loga.authenticationservice.service;

import com.loga.authenticationservice.entity.AuthSession;
import com.loga.authenticationservice.entity.User;
import com.loga.authenticationservice.exception.SessionErrorException;
import com.loga.authenticationservice.exception.UserAlreadyExistsException;
import com.loga.authenticationservice.exception.UserNotFoundException;
import com.loga.authenticationservice.repository.UserRepository;
import com.loga.authenticationservice.repository.AuthSessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Authenticate implements IAuthenticate {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthSessionRepository authSessionRepository;

    @Override
    public AuthSession register(User user) {

        boolean userExist = userRepository
                .findByUsernameOrEmailIgnoreCase(user.getUsername(),user.getEmail())
                .isPresent();

        if(userExist){
            throw new UserAlreadyExistsException("User already exist");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AuthSession authSession = generateSession();
        user.addSession(authSession);
        userRepository.save(user);

        return authSession;
    }

    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public AuthSession authenticate(User user) {
        try {
            User signed;

            if(userRepository.findByUsernameIgnoreCase(user.getUsername()).isPresent()){
                signed = userRepository.findByUsernameIgnoreCase(user.getUsername()).get();
                if(!passwordEncoder.matches(user.getPassword(), signed.getPassword())){
                    throw new SessionErrorException("Password is not correct");
                } else if(!signed.getActive()){
                    throw new SessionErrorException("Access not granted");
                }
            }else {
                throw new UserNotFoundException("User does not exists");
            }

            AuthSession authSession = generateSession();
            signed.addSession(authSession);

            userRepository.saveAndFlush(signed);

            return authSession;

        }catch (Exception e){
            throw new SessionErrorException("Failed on session establishements: \n"+e.getMessage());
        }
    }

    private AuthSession generateSession() {
        AuthSession authSession = new AuthSession();
        authSession.setGrant("AUTHENTICATED");
        authSession.setClosed(false);
        authSession.setCreatedAt(new Date(System.currentTimeMillis()));
        return authSession;
    }

    @Override
    public AuthSession authenticate(String token) throws SessionErrorException{
        return authSessionRepository
                .findByToken(token)
                .orElseThrow(() -> new SessionErrorException("Session not found"));
    }

    @Override
    public void logout(String token) {
        AuthSession authSession = authSessionRepository
                .findByToken(token)
                .orElseThrow(()-> new SessionErrorException("Session not found"));

        authSession.setClosedAt(new Date(System.currentTimeMillis()));
        authSession.setClosed(true);
        authSessionRepository.save(authSession);
    }

    @Override
    @Transactional
    public void edit(User user, Long id){
        userRepository
            .findById(id)
            .ifPresent(up -> {
                up.setUsername(user.getUsername());
                up.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.saveAndFlush(up);
            });
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
