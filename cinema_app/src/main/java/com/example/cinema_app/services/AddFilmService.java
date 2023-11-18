package com.example.cinema_app.services;

import com.example.cinema_app.models.Film;
import com.example.cinema_app.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new Exception("Фильм с таким именем уже добавлен в афишу.");
        filmRepository.save(film);
    }
}
