package com.example.cinema_app.services;

import com.example.cinema_app.dto.FilmDto;
import com.example.cinema_app.models.Film;
import com.example.cinema_app.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для фильмов {@link FilmDto}.
 * Сервис собирает данные для представления library/list.html.
 */
@Service
public class FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    private FilmDto transformFilmToFilmDto(Film film) {
        return new FilmDto(film.getFilmName(), film.getDescription(), film.getReleaseYear(),
                film.getDurationInMinutes(), film.getMinimalAge(), film.getPoster(), film.getGenre());
    }

    public Optional<FilmDto> findById(Long id) {
        var filmOptional = filmRepository.findById(id);
        if (filmOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(transformFilmToFilmDto(filmOptional.get()));
    }

    public Collection<FilmDto> findAll() {
        return filmRepository.findAll().stream()
                .map(this::transformFilmToFilmDto).collect(Collectors.toList());
    }
}
