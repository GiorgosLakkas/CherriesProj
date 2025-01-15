package com.example.unimeets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MatchAlgoProject{

    
@Autowired
    public MatchAlgoProject(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
        
    }
    @Transactional
        // Method to find matching users
        public List<String> findMatches(String userString, double threshold) {
            List<String> matchingUsers = new ArrayList<>();
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
    
            try {
                // Connect to the database
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
    
                // Query to fetch all users' first and last names with their matching strings
                String query = "SELECT first_name, last_name, matching_string FROM users_table";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();
    
                double highestPercentage = 0;
                String bestMatch = "";
    
                // Iterate through the result set
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String matchingString = resultSet.getString("matching_string");
    
                    // Compare strings using EventNameMatcher
                    double percentage = EventNameMatcher.getPercentage(userString, matchingString);
    
                    if (percentage >= threshold) {
                        if (percentage > highestPercentage) {
                            highestPercentage = percentage;
                            bestMatch = firstName + " " + lastName;
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

    
