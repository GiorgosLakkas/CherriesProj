package com.example.unimeets;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class SimpleMeetingSearch {

    private final JdbcTemplate jdbcTemplate;

    // Constructor injection for JdbcTemplate
    public SimpleMeetingSearch(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserFilter> searchSimpleMeetingUsers() {
        String query = "SELECT id, age, university, department, gender, interests_matrix, volunteer_matrix, year_of_study " +
                       "FROM user_profile " +
                       "WHERE purpose = ?";
        return jdbcTemplate.query(query, new Object[]{"simple meeting"}, userFilterRowMapper());
    }

    // RowMapper to map database rows to UserFilter objects
    private RowMapper<UserFilter> userFilterRowMapper() {
        return (resultSet, rowNum) -> {
            String id = resultSet.getString("id");
            Integer age = resultSet.getInt("age");
            String university = resultSet.getString("university");
            String departmentOfStudies = resultSet.getString("department");
            String sex = resultSet.getString("gender");
            String interests = resultSet.getString("interests_matrix");
            String volunteeringProjects = resultSet.getString("volunteer_matrix");
            String academicYear = resultSet.getString("year_of_study");

            return new UserFilter(id, age, university, departmentOfStudies, sex, interests, volunteeringProjects, academicYear);
        };
    }
}