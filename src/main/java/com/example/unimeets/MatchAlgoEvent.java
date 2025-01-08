package com.example.unimeets;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
public class MatchAlgoEvent {
    // Method to find users attending an event based on the first word match
    public List<String> findEventAttendees(String eventString) {
        List<String> attendees = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
 
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cherries", "root", "fairyberry");
 
            // Query to fetch all users' names and event matching strings
            String query = "SELECT name, matching_string FROM my_app_user";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
 
            // Extract the first word from the provided event string
            String eventFirstWord = eventString.split(" ")[0];
 
            // Iterate through the result set
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String matchingString = resultSet.getString("matching_string");
                String matchingFirstWord = matchingString.split(" ")[0];
 
                // Check if the first words match
                if (eventFirstWord.equalsIgnoreCase(matchingFirstWord)) {
                    attendees.add(name);
                }
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
        return attendees;
    }
}