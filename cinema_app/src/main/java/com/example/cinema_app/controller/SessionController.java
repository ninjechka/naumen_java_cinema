package com.example.cinema_app.controller;

import com.example.cinema_app.services.SessionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String getAll(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();model.addAttribute("login", login);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean admin = false;
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            admin = true;
        }
        model.addAttribute("admin", admin);
        model.addAttribute("sessions", sessionService.findAll());
        return "/sessions";
    }
}
