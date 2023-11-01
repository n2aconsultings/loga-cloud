package com.loga.authenticationservice.repository;

import com.loga.authenticationservice.entity.AuthSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthSessionRepository extends JpaRepository<AuthSession,Long> {
    Optional<AuthSession> findByToken(String token);
}
