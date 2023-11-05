package com.example.cinema_app.services;

import com.example.cinema_app.models.ERole;
import com.example.cinema_app.models.Film;
import com.example.cinema_app.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AddFilmService {


    private final FilmRepository filmRepository;

    @Autowired
    public AddFilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(Film film) throws Exception {
        Film filmFromDb = filmRepository.findByFilmName(film.getFilmName());
        if (filmFromDb != null)
        {
            throw new Exception("film exist");
        }

        filmRepository.save(film);
    }
}
