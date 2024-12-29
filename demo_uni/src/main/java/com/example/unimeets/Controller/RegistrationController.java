package main.java.com.example.unimeets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import main.java.com.example.unimeets.MyAppUser;
import main.java.com.example.unimeets.MyAppUserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final MyAppUserRepository myAppUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(MyAppUserRepository myAppUserRepository, PasswordEncoder passwordEncoder) {
        this.myAppUserRepository = myAppUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<?> createUser(@Valid @RequestBody MyAppUser user) {
        if (myAppUserRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        myAppUserRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
