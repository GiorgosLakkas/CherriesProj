package com.example.unimeets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchAlgoProject {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MatchAlgoProject(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Transactional 
    // Method to find matching users
    public List<String> findMatches(String userString, double threshold) {
        List<String> matchingUsers = new ArrayList<>();
        
        // SQL query to fetch names and matching strings from the database (MyAppUser table)
        String query = "SELECT name, matching_string FROM my_app_user";
        
        // Using JdbcTemplate to execute the query and map the result to MyAppUser objects
        List<MyAppUser> myAppUsers = jdbcTemplate.query(query, (rs, rowNum) -> {
            String name = rs.getString("name");
            String matchingString = rs.getString("matching_string");

            // Create and return a MyAppUser object
            MyAppUser myAppUser = new MyAppUser(name, "", "", ""); // Create with name, or update as per your logic
            myAppUser.setMatchingString(matchingString);
            return myAppUser;
        });

        EventNameMatcher matcher = new EventNameMatcher();

        // Compare strings using EventNameMatcher
        for (MyAppUser myAppUser : myAppUsers) {
            String name = myAppUser.getName();
            String matchingString = myAppUser.getMatchingString();
            
            // Compare strings using EventNameMatcher
            double percentage = matcher.getPercentage(userString, matchingString);

            if (percentage >= threshold) {
                matchingUsers.add(name); // Add name to result list if it matches
            }
        }

        return matchingUsers; // Return a list of matching user names
    }
}
