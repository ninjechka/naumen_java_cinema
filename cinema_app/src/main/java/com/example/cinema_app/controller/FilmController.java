package com.example.cinema_app.controller;

import com.example.cinema_app.dto.FilmDto;
import com.example.cinema_app.models.Film;
import com.example.cinema_app.services.FilmService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Контроллер для афиши с фильмами
 */
@Controller
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    /**
     * Обработка запроса афиши. Добавление для отображения в модель всех фильмов, найденных в бд
     * @param model
     * @return Форма с афишей
     */
    @GetMapping("/films")
    public String getAll(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();model.addAttribute("login", login);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean admin = false;
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            admin = true;
        }
        model.addAttribute("admin", admin);
        model.addAttribute("films", filmService.findAll());
        return "/films";
    }

    /**
     * Обработка запроса на добавление нового фильма в афишу
     * @param model
     * @return форма добавления фильма
     */
    @GetMapping("/addfilm")
    public String addFilm(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        model.addAttribute("genres", filmService.getGenreCollection());
        return "/addfilm";
    }

    /**
     * Добавить новый фильм в афишу
     * @param film Новый фильм
     * @param model
     * @return перенаправление на афишу в случае успешного добавления фильма
     */
    @PostMapping("/addfilm")
    public String addFilm(Film film, Model model)
    {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        try
        {
            filmService.addFilm(film);
            return "redirect:/films";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", ex.getMessage());
            return "addfilm";
        }
    }
}
