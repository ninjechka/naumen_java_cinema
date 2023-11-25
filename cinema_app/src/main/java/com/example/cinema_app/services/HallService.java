package com.example.cinema_app.services;

import com.example.cinema_app.dto.HallDto;
import com.example.cinema_app.models.Hall;
import com.example.cinema_app.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class HallService {

    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    private Collection<Integer> getRowCountCollection(Hall hall) {
        return IntStream.rangeClosed(1, hall.getRowCount()).boxed().toList();
    }

    private Collection<Integer> getPlaceCountCollection(Hall hall) {
        return IntStream.rangeClosed(1, hall.getPlaceCount()).boxed().toList();
    }

    private HallDto transformToHallDto(Hall hall) {
        return new HallDto(hall.getHallId(), hall.getHallName(), getRowCountCollection(hall), getPlaceCountCollection(hall));
    }

    public Optional<HallDto> findById(Long id) {
        var hallOptional = hallRepository.findById(id);
        if (hallOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(transformToHallDto(hallOptional.get()));
    }

    public HallDto findByName(String name) {
        var hall = hallRepository.findByHallName(name);
        return transformToHallDto(hall);
    }
}
