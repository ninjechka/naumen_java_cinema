package com.example.cinema_app.controller;

import com.example.cinema_app.models.Film;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String addFilm(Film film, Model model)
    {
        return "redirect:/main_page";
    }
}
