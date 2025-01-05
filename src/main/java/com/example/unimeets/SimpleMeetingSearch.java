package com.example.unimeets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SimpleMeetingSearch {

    private Connection databaseConnection;
    public SimpleMeetingSearch(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    public List<UserFilter> searchSimpleMeetingUsers() {
        List<UserFilter> users = new ArrayList<>();
        String query = "SELECT age, university, department, gender, interests_matrix, volunteer_matrix, year_of_study " +
                       "FROM user_profile " +
                       "WHERE purpose = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            statement.setString(1, "simple meeting");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer age = resultSet.getInt("age");
                    String university = resultSet.getString("university");
                    String departmentOfStudies = resultSet.getString("department");
                    String sex = resultSet.getString("gender");
                    String interests = resultSet.getString("interests_matrix");
                    String volunteeringProjects = resultSet.getString("volunteer_matrix");
                    String academicYear = resultSet.getString("year_of_studies");

                    UserFilter user = new UserFilter(age, university, departmentOfStudies, sex, interests, volunteeringProjects, academicYear);
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
