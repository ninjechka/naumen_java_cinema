package com.example.cinema_app.controller;

import com.example.cinema_app.dto.ShowtimeDto;
import com.example.cinema_app.services.ShowtimeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер для страницы сеансов
 */
@Controller
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    /**
     * Обработка запроса сеансов. Добавление для отображения в модель всех сеансов, найденных в бд
     * @param model
     * @return Форма с сеансами
     */
    @GetMapping("/showtime")
    public String getAll(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();model.addAttribute("login", login);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean admin = false;
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            admin = true;
        }
        model.addAttribute("admin", admin);
        model.addAttribute("showtime", showtimeService.findAll());
        return "/showtime";
    }

    /**
     * Обработка добавления нового сеанса в афишу
     * @param model
     * @return форма добавления сеанса
     */
    @GetMapping("/addshowtime")
    public String addShowtime(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        model.addAttribute("halls", showtimeService.getHallCollection());
        return "/addshowtime";
    }

    /**
     * Добавление нового сеанса
     * @param showtimeDto dto нового сеанса
     * @param model
     * @return перенаправление на страницу сеансов в случае успешного добавления сеанса
     */
    @PostMapping("/addshowtime")
    public String addShowtime(ShowtimeDto showtimeDto, Model model)
    {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        try
        {
            showtimeService.addShowtime(showtimeDto);
            return "redirect:/showtime";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", ex.getMessage());
            return "addshowtime";
        }
    }
}
