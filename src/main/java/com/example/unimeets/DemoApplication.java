package com.example.unimeets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

    
    private final MatchAlgoFriend matchAlgoFriend;
    private final MatchAlgoProject matchAlgoProject;
    private final MatchAlgoEvent matchAlgoEvent;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    public DemoApplication(MatchAlgoFriend matchAlgoFriend, MatchAlgoProject matchAlgoProject, MatchAlgoEvent matchAlgoEvent) {
        this.matchAlgoFriend = matchAlgoFriend;
        this.matchAlgoProject = matchAlgoProject;
        this.matchAlgoEvent = matchAlgoEvent;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the application!");

        MyAppUser newUser = registerUser(scanner);

         // Δημιουργία και έλεγχος του UserProfile        
         UserProfile userProfile = createUserProfile(scanner, newUser, userProfileRepository);
        
        // Εμφάνιση αποτελεσμάτων
        System.out.println("\n--- User Profile ---");
        System.out.println("Age: " + userProfile.getAge());
        System.out.println("Gender: " + userProfile.getGender());
        System.out.println("University: " + userProfile.getUniversity());
        System.out.println("Department: " + userProfile.getDepartment());
        System.out.println("Year of Study: " + userProfile.getYearOfStudy());
        System.out.println("Interests: " + userProfile.getInterests());
        System.out.println("Volunteer Activities: " + userProfile.getVolunteerActivities());
        
        int choice;
        do {
            System.out.println("Please enter your choice:");
            System.out.println("1: socializing");
            System.out.println("2: event");
            System.out.println("for choice 2 enter name of event ;)");
            System.out.println("3: assignment");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            choice = scanner.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        } while (choice < 1 || choice > 3);

        System.out.println("You selected choice: " + choice);
        switch (choice) {
            case 1:
                List<String> matches = matchAlgoFriend.getUsersAbove95Match(userProfile);
                if (matches.isEmpty()) {
                    System.out.println("No matches found with 95% or higher.");
                } else {
                    System.out.println("High-match users for socializing:");
                    matches.forEach(System.out::println);
                }
                break;
            case 2:

                MatchAlgoEvent event = new MatchAlgoEvent(jdbcTemplate);
                scanner.nextLine();
        
                String eventString = null;
                while (eventString == null || eventString.trim().isEmpty()) {
                    System.out.println("Enter the event name to match against:");
                    eventString = scanner.nextLine().trim();
                    newUser.setMatchingString(eventString); 
            
                    if (eventString.isEmpty()) {
                        System.out.println("Event name cannot be empty. Please try again.");
                    }
                }

                myAppUserRepository.save(newUser);
            
                List<String> matches2 = event.findEventAttendees(eventString);
                
                if (matches2.isEmpty()) {
                    System.out.println("No matches found");
                } else {
                    System.out.println("Matches:");
                    matches2.forEach(System.out::println);
                }
                break;
            case 3:
                // Pass JdbcTemplate to MatchAlgoProject constructor
                MatchAlgoProject project = new MatchAlgoProject(jdbcTemplate);
            
                scanner.nextLine();
            
                // Get user string with proper validation
                String userString = null;
                while (userString == null || userString.trim().isEmpty()) {
                    System.out.println("Enter the user string to match against:");
                    userString = scanner.nextLine().trim();
                    newUser.setMatchingString(userString); 
            
                    if (userString.isEmpty()) {
                        System.out.println("User string cannot be empty. Please try again.");
                    }
                }
                
                myAppUserRepository.save(newUser);
                
                // Get the match threshold
                double threshold = 0.0;
                while (threshold <= 0 || threshold > 1) {
                    try {
                        System.out.println("Enter the match threshold (e.g., 0.95):");
                        threshold = scanner.nextDouble();
            
                        if (threshold <= 0 || threshold > 1) {
                            System.out.println("Invalid threshold. Please enter a value between 0 and 1.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Threshold must be a number between 0 and 1.");
                        scanner.next(); // Clear invalid input
                    }
                }
            
                // Find the matching users
                List<String> matches3 = project.findMatches(userString, threshold);
            
                if (matches3.isEmpty()) {
                    System.out.println("No matches found for the provided string with threshold: " + threshold);
                } else {
                    System.out.println("Matches:");
                    matches3.forEach(System.out::println);
                }
                break;
        }
    }

    private MyAppUser registerUser( Scanner scanner) {
            //Δημιουργία και έλεγχος του MyAppUser (registration)
        // ή login
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = passwordValidator.validatePassword();
        MyAppUser newUser = new MyAppUser(name, username, email, password);
        newUser.setPassword(password); // Set the validated password

        // Save the user to the database
        myAppUserRepository.save(newUser); // Save the new user
        return newUser; // Return the saved user

    }

    private static UserProfile createUserProfile(Scanner scanner, MyAppUser newUser, UserProfileRepository userProfileRepository) {
        UserProfile userProfile = new UserProfile();

        while (true) {
            try {
                System.out.println("\n--- Create Your Profile ---");

                // Εισαγωγή ηλικίας
                int age = UserSelection.getValidAge(scanner);
                userProfile.setAge(age);

                // Εισαγωγή φύλου
                List<String> genderOptions = UserSelection.getGenderOptions();
                userProfile.setGender(UserSelection.getUserSelection(scanner, genderOptions, "Please select your gender"));

                // Εισαγωγή πανεπιστημίου
                List<String> universityOptions = UserSelection.getUniversityOptions();
                userProfile.setUniversity(UserSelection.getUserSelection(scanner, universityOptions, "Please select your university"));

                // Εισαγωγή τμήματος
                List<String> departmentOptions = UserSelection.getDepartmentOptions();
                userProfile.setDepartment(UserSelection.getUserSelection(scanner, departmentOptions, "Please select your department"));

                // Εισαγωγή έτους σπουδών
                List<String> yearOptions = UserSelection.getYearOptions();
                userProfile.setYearOfStudy(UserSelection.getUserSelection(scanner, yearOptions, "Please select your year of study"));

            
                List<String> interests = UserSelection.getInterestsOptions();
                List<String> selectedInterests = UserSelection.getMultipleUserSelections(scanner, interests, 
                "Please select your interests", 5);
                userProfile.setInterests(selectedInterests, interests);

                List<String> volunteerActivities = UserSelection.getVolunteerActivitiesOptions();
                List<String> selectedVolunteerActivities = UserSelection.getMultipleUserSelections(scanner, volunteerActivities, 
                "Please select your volunteering activities (optional)", 3);
                userProfile.setVolunteerActivities(selectedVolunteerActivities, volunteerActivities);
                
                userProfile.setMyAppUser(newUser);
                // Επικύρωση προφίλ
                if (!ValidatorUserProfile.validate(userProfile)) {
                    System.out.println("Please complete the required fields correctly.");
                    continue;
                }

                userProfileRepository.save(userProfile);
                return userProfile;

            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
    public static void handleAssignmentCreation(Scanner scanner, AssignmentRepository assignmentRepository) {
        System.out.println("\nWould you like to create an assignment? (yes/no):");
        String createAssignmentChoice = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(createAssignmentChoice)) {
            // Create and save the assignment
            Assignment assignment = Assignment.createAssignment(); // Assuming this method exists in the Assignment class
            assignmentRepository.save(assignment); // Save the assignment to the database

            System.out.println("Assignment created and saved: " + assignment);
            System.out.println("\nThank you for using the application!");
        } else {
            System.out.println("No assignment was created.");
        }
    }
}

