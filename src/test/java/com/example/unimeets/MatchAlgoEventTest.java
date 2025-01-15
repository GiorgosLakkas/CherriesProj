package com.example.unimeets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MatchAlgoEventTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private MatchAlgoEvent matchAlgoEvent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        matchAlgoEvent = new MatchAlgoEvent(namedParameterJdbcTemplate);
    }

    @Test
    public void testFindEventAttendees_WithMatchingUsers() {
        String eventString = "Hackathon 2025";

        // Mock the behavior of namedParameterJdbcTemplate
        when(namedParameterJdbcTemplate.query(
                eq("SELECT * FROM events WHERE matching_string LIKE :event"),
                any(Map.class),
                any(RowMapper.class)
        )).thenAnswer(invocation -> List.of(
                Map.of("name", "Alice", "matching_string", "Hackathon Party"),
                Map.of("name", "Bob", "matching_string", "Hackathon Fun")
        ));

        // Call the method under test
        List<String> attendees = matchAlgoEvent.findEventAttendees(eventString);

        // Assertions
        assertNotNull(attendees);
        assertEquals(2, attendees.size());
        assertTrue(attendees.contains("Alice"));
        assertTrue(attendees.contains("Bob"));
    }

    @Test
    public void testFindEventAttendees_WithNoMatchingUsers() {
        String eventString = "Nonexistent Event";

        // Mock the behavior of namedParameterJdbcTemplate
        when(namedParameterJdbcTemplate.query(
                eq("SELECT * FROM events WHERE matching_string LIKE :event"),
                any(Map.class),
                any(RowMapper.class)
        )).thenReturn(List.of());

        // Call the method under test
        List<String> attendees = matchAlgoEvent.findEventAttendees(eventString);

        // Assertions
        assertNotNull(attendees);
        assertTrue(attendees.isEmpty());
    }
}
