package com.vti.auth_service.entity;

import com.vti.auth_service.entity.enums.Role;
import com.vti.auth_service.oauth2.common.AuthProvider;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Builder
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 20, nullable = false)
    private String username;

    @Column(name = "firstname", length = 20, nullable = true)
    private String firstName;

    @Column(name = "lastname", length = 20, nullable = true)
    private String lastName;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", length = 120, nullable = false)
    private String password;

    @Column(name = "access_token", length = 255, nullable = true)
    private String accessToken;

    @Column(name = "refresh_token", length = 255, nullable = true)
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "provider", columnDefinition = "ENUM('local', 'facebook', 'google', 'github') DEFAULT 'local'")
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(name = "provider_id", length = 100, nullable = true)
    private String providerId;

    @Column(name = "image_url", length = 200, nullable = true)
    private String imageUrl;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
