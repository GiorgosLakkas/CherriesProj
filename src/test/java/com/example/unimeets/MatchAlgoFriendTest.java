package com.example.unimeets;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class MatchAlgoFriendTest {

    @MockBean
    private UserProfileRepository userProfileRepository;
    
    @MockBean
    private MatchAlgoFriend matchAlgoFriend;

    private UserProfile testUserProfile;
    private UserProfile otherUserProfile1;
    private UserProfile otherUserProfile2;

    @BeforeEach
    public void setUp() {

        // Mock global interests and volunteer activities
        List<String> allInterests = Arrays.asList("Coding", "Gaming", "Music", "Reading", "Painting", "Dancing");
        List<String> allActivities = Arrays.asList("Volunteering", "Teaching", "Charity Work");

        // Mocking test user profile
        testUserProfile = new UserProfile();
        testUserProfile.setUniversity("Test University");
        testUserProfile.setDepartment("Computer Science");
        testUserProfile.setAge(25);
        testUserProfile.setGender("Male");
        testUserProfile.setInterests(Arrays.asList("Coding", "Gaming", "Music"), allInterests);
        testUserProfile.setVolunteerActivities(Arrays.asList("Volunteering", "Teaching"), allActivities);

        MyAppUser testUser = new MyAppUser();
        testUser.setId(1L);
        testUser.setName("Test User");
        testUserProfile.setMyAppUser(testUser);

        // Mocking other user profile 1
        otherUserProfile1 = new UserProfile();
        otherUserProfile1.setUniversity("Test University");
        otherUserProfile1.setDepartment("Computer Science");
        otherUserProfile1.setAge(25);
        otherUserProfile1.setGender("Female");
        otherUserProfile1.setInterests(Arrays.asList("Coding", "Reading", "Music"), allInterests);
        otherUserProfile1.setVolunteerActivities(Arrays.asList("Teaching", "Charity Work"), allActivities);

        MyAppUser otherUser1 = new MyAppUser();
        otherUser1.setId(2L);
        otherUser1.setName("User One");
        otherUserProfile1.setMyAppUser(otherUser1);

        // Mocking other user profile 2
        otherUserProfile2 = new UserProfile();
        otherUserProfile2.setUniversity("Another University");
        otherUserProfile2.setDepartment("Mathematics");
        otherUserProfile2.setAge(23);
        otherUserProfile2.setGender("Female");
        otherUserProfile2.setInterests(Arrays.asList("Painting", "Dancing", "Music"), allInterests);
        otherUserProfile2.setVolunteerActivities(Arrays.asList("Charity Work"), allActivities);

        MyAppUser otherUser2 = new MyAppUser();
        otherUser2.setId(3L);
        otherUser2.setName("User Two");
        otherUserProfile2.setMyAppUser(otherUser2);

        // Mock repository behavior
        Mockito.when(userProfileRepository.findAll()).thenReturn(Arrays.asList(testUserProfile, otherUserProfile1, otherUserProfile2));
    }

    @Test
    public void testGetUsersAbove95Match() {
        List<String> highMatchUsers = matchAlgoFriend.getUsersAbove95Match(testUserProfile);

        assertNotNull(highMatchUsers);
        assertTrue(highMatchUsers.contains("User One (0.75)")); // Adjust this based on your match calculation logic
        assertFalse(highMatchUsers.contains("User Two")); // Adjust based on match percentage
    }

    @Test
    public void testCalculateMatchInterest() {
        String[] user1 = new String[22];
        String[] user2 = new String[22];
        user1[4] = "Coding";
        user1[5] = "Music";
        user2[4] = "Coding";
        user2[5] = "Painting";

        double matchInterest = matchAlgoFriend.CalculateMatchInterest(user1, user2);
        assertEquals(0.5, matchInterest, 0.01);
    }

    @Test
    public void testAdaptMatchInterest() {
        assertEquals(0.09, matchAlgoFriend.AdaptMatchInterest(0.1), 0.01);
        assertEquals(0.44, matchAlgoFriend.AdaptMatchInterest(0.4), 0.01);
        assertEquals(0.84, matchAlgoFriend.AdaptMatchInterest(0.7), 0.01);
    }

    @Test
    public void testCalculateMatchBase() {
        String[] user1 = new String[22];
        String[] user2 = new String[22];
        user1[1] = "Computer Science";
        user1[2] = "25";
        user2[1] = "Computer Science";
        user2[2] = "25";

        double matchBase = matchAlgoFriend.CalculateMatchBase(user1, user2);
        assertEquals(1.0, matchBase, 0.01);

        user2[2] = "23";
        matchBase = matchAlgoFriend.CalculateMatchBase(user1, user2);
        assertEquals(0.5, matchBase, 0.01);

        user2[1] = "Mathematics";
        matchBase = matchAlgoFriend.CalculateMatchBase(user1, user2);
        assertEquals(0.0, matchBase, 0.01);

            // Mock repository behavior
    Mockito.when(userProfileRepository.findAll()).thenReturn(Arrays.asList(testUserProfile, otherUserProfile1, otherUserProfile2));
    }
}
