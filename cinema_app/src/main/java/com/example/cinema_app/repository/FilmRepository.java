package com.example.cinema_app.repository;


import com.example.cinema_app.models.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для фильмов
 */
public interface FilmRepository extends CrudRepository<Film, Long> {
    Optional<Film> findByFilmId(Long id);
    List<Film> findAll();
    Film findByFilmName(String filmName);
}

