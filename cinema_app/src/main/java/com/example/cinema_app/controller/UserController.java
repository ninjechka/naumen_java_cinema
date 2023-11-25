package com.example.cinema_app.controller;

import com.example.cinema_app.models.User;
import com.example.cinema_app.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер для пользователей
 */
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/main_page";
    }

    /**
     * Обработка запроса регистрации
     * @return форма регистрации
     */
    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    /**
     * Зарегистрировать нового пользователя
     * @param user новый пользователь
     * @param model
     * @return форма авторизации
     */
    @PostMapping("/registration")
    public String adduser(User user, Model model)
    {
        try
        {
            userService.addUser(user);

        }
        catch (Exception ex)
        {
            model.addAttribute("message", ex.getMessage());
            return "registration";
        }
        return "redirect:/login";
    }
}
