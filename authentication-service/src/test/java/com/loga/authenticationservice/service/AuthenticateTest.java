package com.loga.authenticationservice.service;

import com.loga.authenticationservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticateTest {

    @Autowired
    private IAuthenticate authenticate;

    @Test
    void register() {
        User user = new User("admin","secret");
        user.setRole("ADMIN");
        user.setActive(true);
        var added = authenticate.register(user);
        assertNotNull(added,"Failed to registrate User");
    }

    @Test
    void authenticate() {
        User user = new User("admin","secret");
        var authenticated = authenticate.authenticate(user);
        assertNotNull(authenticated,"Failed to authenticate User");
    }
}