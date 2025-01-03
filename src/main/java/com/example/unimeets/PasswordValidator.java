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
        Scanner scanner = new Scanner(System.in);
        String password;

        while (true) {
            password = scanner.nextLine().trim();
            if(!hasEnough(password)){
                System.out.print("Your password should have at least 7 characters");
                continue;
            }
   
            System.out.println("Confirm your password: ");
             String confirmedPassword = scanner.nextLine().trim();

            if (!password.equals(confirmedPassword)) {
            System.out.println("Passwords do not match.Please reenter your password:");
            continue;
            }
            break;
        }
        return passwordEncoder.encode(password);
    }

    public static boolean hasEnough(String password) {
        return password.length() >= 7;
    }
}
