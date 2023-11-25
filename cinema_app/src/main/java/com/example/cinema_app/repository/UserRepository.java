package com.example.cinema_app.repository;

import com.example.cinema_app.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для пользователей
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
