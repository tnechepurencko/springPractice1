package com.example.springPractice1.controller;

import com.example.springPractice1.domain.Message;
import com.example.springPractice1.repos.MessageR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageR messageR;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greetings";
    }

    @GetMapping("/main")
    public String mainPage(Map<String, Object> model) {
        Iterable<Message> messages = messageR.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageR.save(message);

        Iterable<Message> messages = messageR.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageR.findByTag(filter);
        } else {
            messages = messageR.findAll();
        }
        model.put("messages", messages);

        return "main";
    }
}
