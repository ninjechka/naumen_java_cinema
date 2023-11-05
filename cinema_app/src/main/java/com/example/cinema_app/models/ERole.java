package com.example.cinema_app.models;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
