package com.example.springPractice1.controller;

import com.example.springPractice1.domain.Role;
import com.example.springPractice1.domain.User;
import com.example.springPractice1.repos.UserR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserR userR;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDB = userR.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.put("message", "User Exists");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userR.save(user);

        return "redirect:/login";
    }
}
