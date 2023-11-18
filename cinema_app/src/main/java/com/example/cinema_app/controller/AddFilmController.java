package com.example.cinema_app.controller;

import com.example.cinema_app.models.Film;
import com.example.cinema_app.services.AddFilmService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddFilmController {

    private final AddFilmService addFilmServicefilmService;

    public AddFilmController(AddFilmService addFilmService) {
        this.addFilmServicefilmService = addFilmService;
    }

    @GetMapping("/addfilm")
    public String addFilm(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        return "/addfilm";
    }

    @PostMapping("/addfilm")
    public String addFilm(Film film, Model model)
    {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        try
        {
            addFilmServicefilmService.addFilm(film);
            return "redirect:/films";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", ex.getMessage());
            return "addfilm";
        }
    }
}