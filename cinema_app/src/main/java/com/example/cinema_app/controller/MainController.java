package com.example.cinema_app.controller;

import com.example.cinema_app.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping({"/", "/main_page"})
    public String mainPage(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("login", login);
        return "main_page";
    }
}
