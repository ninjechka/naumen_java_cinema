package com.example.cinema_app.repository;

import com.example.cinema_app.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для залов
 */
public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findByHallId(Long id);
    List<Hall> findAll();
    Hall findByHallName(String hallName);
}
