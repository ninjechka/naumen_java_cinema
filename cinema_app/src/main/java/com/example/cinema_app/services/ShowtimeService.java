package com.example.cinema_app.services;

import com.example.cinema_app.dto.ShowtimeDto;
import com.example.cinema_app.models.Film;
import com.example.cinema_app.models.Hall;
import com.example.cinema_app.models.Showtime;
import com.example.cinema_app.repository.FilmRepository;
import com.example.cinema_app.repository.HallRepository;
import com.example.cinema_app.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с сеансами
 */
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

    /**
     * @param showtime сеанс
     * @return название фильма данного сеанса
     */
    String getFilm(Showtime showtime) {
        List<Film> films = showtime.getFilms();
        if (!films.isEmpty())
            return filmRepository.findByFilmId(films.get(0).getFilmId()).get().getFilmName();
        else
            return "Нет фильма с таким id";
    }

    /**
     * @param showtime сеанс
     * @return название зала, в котором данный сеанс проходит
     */
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

    /**
     * Добавить сеанс в бд
     * @param showtimeDto - dto сеанса
     * @throws Exception
     */
    public void addShowtime(ShowtimeDto showtimeDto) throws Exception {
        Film film = filmRepository.findByFilmName(showtimeDto.getFilm());
        if (film == null) {
            throw new Exception("Фильм с таким названием не найден");
        }

        Hall hall = hallRepository.findByHallName(showtimeDto.getHall());
        if (hall == null) {
            throw new Exception("Зал с таким названием не найден");
        }

        if (showtimeDto.getStartTime().isBefore(LocalDateTime.now().minusMinutes(1))) {
            throw new Exception("Сеанс не может начинаться раньше текущего времени");
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
        /**
         * вычисляем время окончания сеанса, прибавляя длительность фильма к времени начала сеанса
         * и 15 минут на подготовку зала и рекламу
         */
        showtime.setEndTime(showtime.getStartTime().plusMinutes(film.getDurationInMinutes() + 15));
        showtime.setPrice(showtimeDto.getPrice());
        showtimeRepository.save(showtime);
    }

    public Collection<String> getHallCollection() {
        List<Hall> halls = hallRepository.findAll();
        List<String> stringList = new ArrayList<>();
        for (Hall hall: halls) {
            stringList.add(hall.getHallName());
        }
        return stringList;
    }

    public Collection<ShowtimeDto> findAll() {
        return showtimeRepository.findAllActualShowtime().stream()
                .map(this::transformShowtimeToShowtimeDto).collect(Collectors.toList());
    }

    public ShowtimeDto findByIdDto(int id) {
        return transformShowtimeToShowtimeDto(showtimeRepository.findById((long)id).get());
    }

    public Showtime findById(int id) {
        return showtimeRepository.findById((long)id).get();
    }
}
