package com.example.cinema_app.services;

import com.example.cinema_app.dto.ShowtimeDto;
import com.example.cinema_app.models.Film;
import com.example.cinema_app.models.Hall;
import com.example.cinema_app.models.Showtime;
import com.example.cinema_app.repository.FilmRepository;
import com.example.cinema_app.repository.HallRepository;
import com.example.cinema_app.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;

    @Autowired
    public ShowtimeService(ShowtimeRepository showtimeRepository,
                           FilmRepository filmRepository, HallRepository hallRepository) {
        this.showtimeRepository = showtimeRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    String getFilm(Showtime showtime) {
        List<Film> films = showtime.getFilms();
        if (!films.isEmpty())
            return filmRepository.findByFilmId(films.get(0).getFilmId()).get().getFilmName();
        else
            return "Нет фильма с таким id";
    }

    String getHall(Showtime showtime) {
        List<Hall> halls = showtime.getHalls();
        if (!halls.isEmpty())
            return hallRepository.findByHallId(halls.get(0).getHallId()).get().getHallName();
        else
            return "Нет зала с таким id";
    }

    private ShowtimeDto transformShowtimeToShowtimeDto(Showtime showtime) {
        return new ShowtimeDto(showtime.getShowtimeId(), getFilm(showtime),
                getHall(showtime), showtime.getStartTime(), showtime.getEndTime(), showtime.getPrice());
    }

    public Collection<ShowtimeDto> findAll() {
        return showtimeRepository.findAll().stream()
                .map(this::transformShowtimeToShowtimeDto).collect(Collectors.toList());
    }
}
