package com.example.unimeets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/signup.html")
    public String register() {
        return "register";
    }

    @GetMapping("/homescr.html") 
    public String home() {
        return "home";
    }
}
