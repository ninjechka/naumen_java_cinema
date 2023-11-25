package com.example.cinema_app.services;

import com.example.cinema_app.models.Ticket;
import com.example.cinema_app.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * Сервис для работы с билетами
 */
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket save(Ticket ticket) throws Exception {
        for (var film: ticket.getShowtime().getFilms()) {
            if (film.getMinimalAge() > ticket.getUser().getAge())
                throw new Exception("Минимальный возраст для просмотра фильма ".concat( String.valueOf(film.getMinimalAge())));
        }
        var sameTicket = ticketRepository.findByRowNumberAndPlaceNumber(ticket.getRowNumber(), ticket.getPlaceNumber());
        if (!sameTicket.isEmpty() && Objects.equals(sameTicket.get().getShowtime().getShowtimeId(), ticket.getShowtime().getShowtimeId()))
            throw new Exception("Это место занято");

        return ticketRepository.save(ticket);
    }

    public Collection<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
