package com.example.cinema_app.repository;


import com.example.cinema_app.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для фильмов
 */
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findByFilmId(Long id);
    List<Film> findAll();
    Film findByFilmName(String filmName);
}

