package com.example.cinema_app.services;

import com.example.cinema_app.dto.SessionDto;
import com.example.cinema_app.models.Session;
import com.example.cinema_app.repository.FilmRepository;
import com.example.cinema_app.repository.HallRepository;
import com.example.cinema_app.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository,
                                    FilmRepository filmRepository, HallRepository hallRepository) {
        this.sessionRepository = sessionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    String getFilm(Session session) {
        var filmOptional = filmRepository.findByFilmId(session.getFilm().getFilmId());
        if (filmOptional.isEmpty()) {
            return "No movie with this id is presented.";
        }
        return filmOptional.get().getFilmName();
    }

    String getHall(Session session) {
        var hallOptional = hallRepository.findByHallId(session.getHall().getHallId());
        if (hallOptional.isEmpty()) {
            return "No hall with this id is presented.";
        }
        return hallOptional.get().getHallName();
    }

    private SessionDto transformSessionToSessionDto(Session session) {
        return new SessionDto(session.getSessionId(), getFilm(session),
                getHall(session), session.getStartTime(), session.getEndTime(), session.getPrice());
    }

    public Optional<SessionDto> findByIdDto(Long id) {
        var filmSessionOptional = sessionRepository.findById(id);
        if (filmSessionOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(transformSessionToSessionDto(filmSessionOptional.get()));
    }

    public Collection<SessionDto> findAll() {
        return sessionRepository.findAll().stream()
                .map(this::transformSessionToSessionDto).collect(Collectors.toList());
    }
}
