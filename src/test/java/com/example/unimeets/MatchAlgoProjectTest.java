package com.example.unimeets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MatchAlgoProjectTest {

    private MatchAlgoProject matchAlgoProject;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate; // Correctly named

    @Mock
    private EventNameMatcher eventNameMatcher;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Initialize matchAlgoProject with the mocked NamedParameterJdbcTemplate
        matchAlgoProject = new MatchAlgoProject(namedParameterJdbcTemplate); // Use the mock
    }

    @Test
    void testFindMatches_withValidInput() {
        String userString = "Test Project";
        double threshold = 80.0;

        // Mock the database response using NamedParameterJdbcTemplate with queryForList
        when(namedParameterJdbcTemplate.queryForList(anyString(), any(MapSqlParameterSource.class), eq(MyAppUser.class)))
                .thenAnswer(invocation -> {
                    return Arrays.asList(
                        new MyAppUser("Alice", "alice_username", "alice_email", "alice_password", "matching_string_1"),
                        new MyAppUser("Bob", "bob_username", "bob_email","bob_password", "matching_string_2")
                    );
                });

        // Mock EventNameMatcher behavior
        when(eventNameMatcher.getPercentage(userString, "matching_string_1")).thenReturn(85.0);
        when(eventNameMatcher.getPercentage(userString, "matching_string_2")).thenReturn(75.0);

        // Call the method under test
        List<String> matches = matchAlgoProject.findMatches(userString, threshold);

        // Verify results
        assertEquals(1, matches.size());
        assertTrue(matches.contains("Alice")); // Alice matches the threshold
    }

    @Test
    void testFindMatches_withEmptyUserString() {
        String userString = "";
        double threshold = 80.0;

        // Call the method under test
        List<String> matches = matchAlgoProject.findMatches(userString, threshold);

        // Verify results: If the user string is empty, there should be no matches
        assertTrue(matches.isEmpty());
    }

    @Test
    void testFindMatches_withNoMatchingUsers() {
        String userString = "Test Project";
        double threshold = 90.0;

        // Mock the database response using NamedParameterJdbcTemplate with queryForList
        when(namedParameterJdbcTemplate.queryForList(anyString(), any(MapSqlParameterSource.class), eq(MyAppUser.class)))
                .thenAnswer(invocation -> {
                    return Arrays.asList(
                        new MyAppUser("Alice", "", "", "matching_string_1"),
                        new MyAppUser("Bob", "", "", "matching_string_2")
                    );
                });

        // Mock EventNameMatcher behavior
        when(eventNameMatcher.getPercentage(userString, "matching_string_1")).thenReturn(70.0); // Below threshold
        when(eventNameMatcher.getPercentage(userString, "matching_string_2")).thenReturn(75.0); // Below threshold

        // Call the method under test
        List<String> matches = matchAlgoProject.findMatches(userString, threshold);

        // Verify results: No matches should be found because both percentages are below the threshold
        assertTrue(matches.isEmpty());
    }

    @Test
    void testFindMatches_withNullUserString() {
        String userString = null;
        double threshold = 80.0;

        // Call the method under test
        List<String> matches = matchAlgoProject.findMatches(userString, threshold);

        // Verify results: If the user string is null, there should be no matches
        assertTrue(matches.isEmpty());
    }
}