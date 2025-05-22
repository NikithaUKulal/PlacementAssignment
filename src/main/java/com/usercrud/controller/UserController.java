package com.usercrud.controller;

import com.usercrud.model.User;
import com.usercrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/", "/users"})
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/users/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        User user = userRepository.findById(id);
        model.addAttribute("user", user);
        return "form";
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute User user) {
        userRepository.update(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.delete(id);
        return "redirect:/users";
    }
} 