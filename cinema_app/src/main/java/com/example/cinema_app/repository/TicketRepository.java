package com.example.cinema_app.repository;

import com.example.cinema_app.models.Showtime;
import com.example.cinema_app.models.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Репозиторий для билетов
 */
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    /**
     * Найти билет по номеру ряда и места
     * @param rowNumber номер ряда
     * @param placeNumber номер места
     * @return Билет
     */
    Optional<Ticket> findByRowNumberAndPlaceNumber(int rowNumber, int placeNumber);
    Collection<Ticket> findAll();
}
