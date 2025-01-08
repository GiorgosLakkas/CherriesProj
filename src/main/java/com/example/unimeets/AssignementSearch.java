package com.example.unimeets;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class AssignementSearch {

    private final JdbcTemplate jdbcTemplate;

    // Constructor injection for JdbcTemplate
    public AssignementSearch(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserFilter> searchAssignementUsers() {
        String query = "SELECT id, age, university, department, gender, interests_matrix, volunteer_matrix, year_of_studies " +
                       "FROM user_profile " +
                       "WHERE purpose = ?";

        return jdbcTemplate.query(query, new Object[]{"assignement"}, userFilterRowMapper());
    }

    // RowMapper to map result set to UserFilter objects
    private RowMapper<UserFilter> userFilterRowMapper() {
        return (resultSet, rowNum) -> {
            String id = resultSet.getString("id");
            Integer age = resultSet.getInt("age");
            String university = resultSet.getString("university");
            String departmentOfStudies = resultSet.getString("department");
            String sex = resultSet.getString("gender");
            String interests = resultSet.getString("interests_matrix");
            String volunteeringProjects = resultSet.getString("volunteer_matrix");
            String academicYear = resultSet.getString("year_of_studies");

            // Convert interests to a single String if necessary
            String joinedInterests = interests != null ? interests : "";

            return new UserFilter(id, age, university, departmentOfStudies, sex, joinedInterests, volunteeringProjects, academicYear);
        };
    }
}