package com.example.cinema_app.repository;

import com.example.cinema_app.models.Hall;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HallRepository extends CrudRepository<Hall, Long> {

    Optional<Hall> findByHallId(Long id);
    List<Hall> findAll();

    Hall findByHallName(String hallName);
}
