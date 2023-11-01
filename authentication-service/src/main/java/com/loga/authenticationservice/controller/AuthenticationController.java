package com.loga.authenticationservice.controller;

import com.loga.authenticationservice.entity.AuthSession;
import com.loga.authenticationservice.entity.User;
import com.loga.authenticationservice.exception.SessionErrorException;
import com.loga.authenticationservice.service.IAuthenticate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authentication-service")
public class AuthenticationController {

    @Autowired
    private IAuthenticate authentication;

    @PostMapping(path = "/registrate", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthSession register(HttpServletRequest request, @RequestBody User user){
        AuthSession authSession = authentication.register(user);
        if (authSession!=null){
            authSession.setHost(request.getRemoteAddr());
            return authSession;
        }else
            throw new SessionErrorException("User registration failed");
    }

    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthSession authenticate(HttpServletRequest request, @RequestBody User user) {
        AuthSession authSession = authentication.authenticate(user);
        if(authSession!=null){
            authSession.setHost(request.getRemoteAddr());
            return authSession;
        }
        else
            throw new SessionErrorException("Failed to establish session");
    }

    @GetMapping(path = "/session",produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthSession authenticate(@RequestParam String token){
        return authentication.authenticate(token);
    }

    @GetMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> list() {
        return authentication.allUser();
    }

    @PutMapping(path = "/update/{id}")
    public void update(@RequestBody User user, @PathVariable Long id){
        authentication.edit(user,id);
    }

    @PutMapping(path = "/logout/{token}")
    public void logout(@PathVariable String token){
        authentication.logout(token);
    }

    @DeleteMapping(path = "/users/{id}")
    public void delete(@PathVariable Long id){
        authentication.delete(id);
    }
}
