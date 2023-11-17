package com.example.cinema_app.repository;

import com.example.cinema_app.models.Hall;
import com.example.cinema_app.models.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends CrudRepository<Session, Long> {
    Optional<Session> findBySessionId(Long id);
    List<Session> findAll();

    @Query("SELECT s FROM Session s " +
            "WHERE s.hall = :hall " +
            "AND ((s.startTime >= :startTime AND s.startTime < :endTime) OR " +
            "(s.endTime> :startTime AND s.endTime <= :endTime))")
    List<Session> findSessionsByHallNameAndTimeRange(
            @Param("hall") Hall hall,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
}
