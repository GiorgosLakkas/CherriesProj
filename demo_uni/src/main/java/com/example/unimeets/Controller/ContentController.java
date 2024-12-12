package main.java.com.example.unimeets.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/loginuni.html")
    public String login() {
        return "login";
    }

    @GetMapping("/signup.html")
    public String register() {
        return "register";
    }

    @GetMapping("/index") 
    public String home() {
        return "index";
    }
}
