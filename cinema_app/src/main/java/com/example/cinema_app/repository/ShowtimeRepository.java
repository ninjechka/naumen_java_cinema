package com.example.cinema_app.repository;

import com.example.cinema_app.models.Showtime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для сеансов
 */
public interface ShowtimeRepository extends CrudRepository<Showtime, Long> {
    Optional<Showtime> findByShowtimeId(Long id);
    List<Showtime> findAll();


    /**
     * поиск сеанса по hallId и временному промежутку
     * @param hallId id зала
     * @param startTime дата и время начала сеанса
     * @param endTime дата и время окончания сеанса
     * @return сеанс с указанным hallId и временным промежутком, пересекающимся с указанным
     */
    @Query("SELECT s FROM Showtime s " +
            "JOIN s.halls h " +
            "WHERE h.hallId = :hallId " +
            "AND ((s.startTime >= :startTime AND s.startTime < :endTime) OR " +
            "(s.endTime> :startTime AND s.endTime <= :endTime))")
    List<Showtime> findShowtimeByHallIdAndTimeRange(
            @Param("hallId") Long hallId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
