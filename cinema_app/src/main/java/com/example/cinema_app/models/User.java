package com.example.cinema_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Модель данных для Пользователей
 */
@Table(name = "users")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    /**
     * Имя пользователя
     */
    @Column(unique=true)
    private String username;

    /**
     * Пароль
     */
    private String password;
    /**
     * Имя
     */
    private String name;
    /**
     * Фамилия
     */
    private String surname;
    /**
     * Возраст
     */
    private int age;
    private boolean active;
    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<ERole> roles = new HashSet<>();
}