package com.example.cinema_app.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * Роли пользователей
 */
public enum ERole implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
