package com.example.demo.controller;

import com.example.demo.entity.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;

@Controller
@AllArgsConstructor
@Slf4j
public class UserControllerBean implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public String unloginned() {
        return "signIn-signUp";
    }

    @Override
    public String homePage() {
        return "index";
    }

    @Override
    public String profilePage() {
        return "my-profile";
    }

    // Return the page with form to add a user
    @Override
    public String showAddUserForm(Model model) {
        // Передаем пустой объект User для заполнения в форме
        model.addAttribute("user", new User());
        return "add-user"; // Возвращает HTML-страницу add-user.html
    }

    // Обрабатываем отправку формы и сохраняем пользователя
    @Override
    public String addUser(@ModelAttribute User user, Model model) {
        userService.create(user);  // Сохраняем пользователя
        return "user-added"; // Перенаправление на страницу успеха
    }

    /*@Override
    public String getUserById(@PathVariable Integer id, Model model) throws Exception {
        try {
            UserDto user = userMapper.toDto(userService.findById(id)); // Поиск пользователя по ID
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            model.addAttribute("user", user); // Передаем объект user в модель
            return "user-profile"; // Название шаблона для отображения
        } catch (Exception e) {
            log.error("Error while retrieving user info: ", e);
            model.addAttribute("error", "User not found");
            return "error"; // Возвращаем страницу с ошибкой, если не найден
        }
    }*/

    @Override
    public String getUserByName(@PathVariable String name, Model model) throws Exception {
        try {
            UserDto user = userMapper.toDto(userService.findByName(name)); // Поиск пользователя по ID
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            model.addAttribute("user", user); // Передаем объект user в модель
            return "user-profile"; // Название шаблона для отображения
        } catch (Exception e) {
            log.error("Error while retrieving user info: ", e);
            model.addAttribute("error", "User not found");
            return "error"; // Возвращаем страницу с ошибкой, если не найден
        }
    }

    public String login() {
        return "login-page"; // Вернуть шаблон login-page.html
    }
}
