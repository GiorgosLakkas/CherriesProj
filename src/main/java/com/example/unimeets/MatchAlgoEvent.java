package com.example.unimeets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchAlgoEvent {

     private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MatchAlgoEvent(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to find users attending an event based on the first word match
    public List<String> findEventAttendees(String eventString) {
        List<String> attendees = new ArrayList<>();

        String eventFirstWord = eventString.split(" ")[0];
        // Query to fetch all users' names and event matching strings
        String query = "SELECT name, matching_string FROM my_app_user";
        
                // Use JdbcTemplate to execute the query
                jdbcTemplate.query(query, (rs) -> {
                    String name = rs.getString("name");
                    String matchingString = rs.getString("matching_string");
        
                    if (matchingString != null) {
                        // Extract the first word from the matching string
                        String matchingFirstWord = matchingString.split(" ")[0];
        
                        // Check if the first words match (case-insensitive)
                        if (eventFirstWord.equalsIgnoreCase(matchingFirstWord)) {
                            attendees.add(name);  // Add name to the list if there's a match
                        }
                    }
                });
                return attendees;
            }
        }
        