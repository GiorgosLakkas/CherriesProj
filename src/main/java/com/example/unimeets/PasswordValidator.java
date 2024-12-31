package com.example.unimeets; 

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Scanner;
import org.springframework.stereotype.Component; 

@Component
public class PasswordValidator {

    private final PasswordEncoder passwordEncoder;

    public PasswordValidator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String validatePassword () {
        Scanner s = new Scanner(System.in);
        String password = null;


        System.out.print("Enter your password: ");
        while ( password == null ||!hasEnough(password)) {
            password = s.nextLine();
            if (!hasEnough(password)) {
                System.out.println("Your password should have 7 or more characters.");
                System.out.print("Please reenter your password: ");
            }
        }
        String confirmedPassword = confirmPassword(s, password);

        if (!password.trim().equals(confirmedPassword.trim())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
        return passwordEncoder.encode(password);
    }
    public static String confirmPassword(Scanner s, String password) {
        String confirmedPassword;
        while (true) {
            System.out.print("Confirm your password: ");
            confirmedPassword = s.nextLine();

            if (!password.trim().equals(confirmedPassword.trim())) {
                System.out.println("Passwords do not match. Please try again.");
            } else {
                return confirmedPassword;
            }
        }
    }
    public static boolean hasEnough(String password) {
        return password.length() >= 7;
    }
}

