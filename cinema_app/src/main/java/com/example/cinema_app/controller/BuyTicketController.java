package com.example.cinema_app.controller;

import com.example.cinema_app.dto.TicketDto;
import com.example.cinema_app.models.Ticket;
import com.example.cinema_app.services.HallService;
import com.example.cinema_app.services.ShowtimeService;
import com.example.cinema_app.services.TicketService;
import com.example.cinema_app.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для покупки билетов
 */
@Controller
public class BuyTicketController {
    private final TicketService ticketService;
    private final UserService userService;
    private final ShowtimeService filmSessionService;
    private final HallService hallService;

    public BuyTicketController(TicketService ticketService, UserService userService, ShowtimeService filmSessionService, HallService hallService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.filmSessionService = filmSessionService;
        this.hallService = hallService;

    }

    @GetMapping("/cashdesk/{id}")
    public String getById(Model model,  @PathVariable int id) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);

        if (userService.findByUsername(login) == null)
            return "/registration";
        var showtime = filmSessionService.findByIdDto(id);

        var hallName = showtime.getHall();
        var hallDto = hallService.findByName(hallName);

        model.addAttribute("rows", hallDto.getRowCountCollection());
        model.addAttribute("places", hallDto.getPlaceCountCollection());

        var showtimeDto = filmSessionService.findByIdDto(id);
        model.addAttribute("show", showtimeDto);

        return "/buy";
    }

    /**
     * Забронировать билет
     * @param model
     * @param ticketDto Объект тикета для отображения
     * @return Страницу с результатом бронирования(если успешно забронировали) иначе возврат на страницу бронирования
     */
    @PostMapping("/buy")
    public String buyTicket(Model model,  TicketDto ticketDto)
    {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);

        var ticket = new Ticket();
        ticket.setPlaceNumber(ticketDto.getPlaceNumber());
        ticket.setRowNumber(ticketDto.getRowNumber());
        ticket.setUser(userService.findByUsername(login));
        ticket.setShowtime(filmSessionService.findById(ticketDto.getShowId()));
        Ticket savedTicket = null;

        try {
            savedTicket = ticketService.save(ticket);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return getById(model, ticketDto.getShowId());
        }

        model.addAttribute("ticketId", ticket.getTicketId());
        return "/success";
    }

    /**
     * @param model
     * @return Форма результата бронирования
     */
    @GetMapping("/success")
    public String success(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        return "/success";
    }
}
