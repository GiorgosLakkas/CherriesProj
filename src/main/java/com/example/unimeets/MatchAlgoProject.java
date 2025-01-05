package com.example.unimeets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MatchAlgoProject{
        // Method to find matching users
        public List<String> findMatches(String userString, double threshold) {
            List<String> matchingUsers = new ArrayList<>();
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
    
            try {
                // Connect to the database
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cherries", "root", "fairyberry");
    
                // Query to fetch all users' first and last names with their matching strings
                String query = "SELECT name, matching_string FROM my_app_user";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                EventNameMatcher matcher = new EventNameMatcher();
    
                double highestPercentage = 0;
                String bestMatch = "";
    
                // Iterate through the result set
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String matchingString = resultSet.getString("matching_string");
    
                    // Compare strings using EventNameMatcher
                    double percentage = matcher.getPercentage(userString, matchingString);
    
                    if (percentage >= threshold) {
                        if (percentage > highestPercentage) {
                            highestPercentage = percentage;
                            bestMatch = name;
                        }
                    }
                }
    
                if (!bestMatch.isEmpty()) {
                    matchingUsers.add(bestMatch);
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return matchingUsers;
        }
    }