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

import java.util.*;

@Service
public class AddShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;

    @Autowired
    public AddShowtimeService(ShowtimeRepository showtimeRepository, FilmRepository filmRepository,
                              HallRepository hallRepository) {
        this.showtimeRepository = showtimeRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    public void addShowtime(ShowtimeDto showtimeDto) throws Exception {
        Film film = filmRepository.findByFilmName(showtimeDto.getFilm());
        if (film == null) {
            throw new Exception("Фильм с таким названием не найден");
        }

        Hall hall = hallRepository.findByHallName(showtimeDto.getHall());
        if (hall == null) {
            throw new Exception("Зал с таким названием не найден");
        }

        List<Showtime> showtimeList = showtimeRepository.
                findShowtimeByHallIdAndTimeRange(hall.getHallId(),
                        showtimeDto.getStartTime(), showtimeDto.getStartTime().plusMinutes(film.getDurationInMinutes()));

        if (!showtimeList.isEmpty() && showtimeList.get(0) != null)
        {
            throw new Exception("Данный зал занят на это время другим сеансом");
        }

        Showtime showtime = new Showtime();
        showtime.setShowtimeId(showtimeDto.getId());
        showtime.setFilms(new ArrayList<>());
        showtime.setHalls(new ArrayList<>());
        showtime.getFilms().add(film);
        showtime.getHalls().add(hall);
        showtime.setStartTime(showtimeDto.getStartTime());
        showtime.setEndTime(showtime.getStartTime().plusMinutes(film.getDurationInMinutes()));
        showtime.setPrice(showtimeDto.getPrice());
        showtimeRepository.save(showtime);
    }
}
