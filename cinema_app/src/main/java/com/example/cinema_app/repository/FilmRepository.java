package com.example.cinema_app.repository;


import com.example.cinema_app.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FilmRepository extends CrudRepository<Film, Long> {

    Optional<Film> findByFilmId(int id);
    List<Film> findAll();

    Film findByFilmName(String filmName);
}

