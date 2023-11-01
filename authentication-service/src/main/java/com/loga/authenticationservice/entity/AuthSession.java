package com.loga.authenticationservice.entity;

import com.loga.authenticationservice.vendor.config.Crypto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name = "auth_session")
public class AuthSession implements Serializable, UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "host")
    private String host;

    @Column(name = "token",unique = true,length = 128)
    private String token;

    @Column(name = "is_closed")
    private Boolean closed;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "closed_at")
    private Date closedAt;

    @Column(name = "grant")
    private String grant;

    public AuthSession() {
        this.setToken(Crypto.getInstance(256).generateToken(128));
    }

    public AuthSession(AuthSession authSession) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.grant));
    }

    @Override
    public String getPassword() {
        return getToken();
    }

    @Override
    public String getUsername() {
        return getToken();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonLocked();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isCredentialsNonExpired();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return ! getClosed();
    }
}
