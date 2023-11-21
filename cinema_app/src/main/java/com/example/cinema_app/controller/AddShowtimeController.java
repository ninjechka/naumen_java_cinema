package com.example.cinema_app.controller;

import com.example.cinema_app.dto.ShowtimeDto;
import com.example.cinema_app.services.AddShowtimeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddShowtimeController {

    private final AddShowtimeService addShowtimeService;

    public AddShowtimeController(AddShowtimeService addShowtimeService) {
        this.addShowtimeService = addShowtimeService;
    }

    @GetMapping("/addshowtime")
    public String addShowtime(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        return "/addshowtime";
    }

    @PostMapping("/addshowtime")
    public String addShowtime(ShowtimeDto showtimeDto, Model model)
    {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        try
        {
            addShowtimeService.addShowtime(showtimeDto);
            return "redirect:/showtime";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", ex.getMessage());
            return "addshowtime";
        }
    }
}
