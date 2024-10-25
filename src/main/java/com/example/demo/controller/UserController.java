package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public interface UserController {

    //Unloginned page
    @GetMapping("/")
    public String unloginned();

    //Home page
    @GetMapping("/home")
    public String homePage();

    //User profile
    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public String profilePage();

    //Add user
    @GetMapping("/add-user")
    public String showAddUserForm(Model model);

    @PostMapping("/add-user")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@ModelAttribute User user, Model model);

    //Get user information by id (only for admins)
    /*@GetMapping("/get-user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getUserById(@PathVariable Integer id, Model model) throws Exception;*/

    //Get user information by name
    @GetMapping("/get-user/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public String getUserByName(@PathVariable String name, Model model) throws Exception;

    //login
    @GetMapping("/login")
    public String login();
}
