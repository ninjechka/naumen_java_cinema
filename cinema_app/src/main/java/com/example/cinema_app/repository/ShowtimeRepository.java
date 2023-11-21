package com.example.cinema_app.repository;

import com.example.cinema_app.models.Showtime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ShowtimeRepository extends CrudRepository<Showtime, Long> {
    Optional<Showtime> findByShowtimeId(Long id);
    List<Showtime> findAll();

    @Query("SELECT s FROM Showtime s " +
            "JOIN s.halls h " +
            "WHERE h.hallId = :hallId " +
            "AND ((s.startTime >= :startTime AND s.startTime < :endTime) OR " +
            "(s.endTime> :startTime AND s.endTime <= :endTime))")
    List<Showtime> findShowtimeByHallIdAndTimeRange(
            @Param("hallId") Long hallId,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
}
