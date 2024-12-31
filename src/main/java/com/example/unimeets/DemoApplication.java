package com.example.unimeets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Scanner scanner = new Scanner(System.in);

        // Δημιουργία και έλεγχος του UserProfile
        System.out.println("Welcome to the application!");
        UserProfile userProfile = createUserProfile(scanner);

        // Επιλογή ενδιαφερόντων
        List<String> interests = UserSelection.getInterestsOptions();
        List<String> selectedInterests = UserSelection.getMultipleUserSelections(scanner, interests, 
                "Please select your interests", 5);
        userProfile.setInterests(selectedInterests, interests);

        // Επιλογή εθελοντικών δράσεων
        List<String> volunteerActivities = UserSelection.getVolunteerActivitiesOptions();
        List<String> selectedVolunteerActivities = UserSelection.getMultipleUserSelections(scanner, volunteerActivities, 
                "Please select your volunteering activities (optional)", 3);
        userProfile.setVolunteerActivities(selectedVolunteerActivities, volunteerActivities);

        // Εμφάνιση αποτελεσμάτων
        System.out.println("\n--- User Profile ---");
        System.out.println("Age: " + userProfile.getAge());
        System.out.println("Gender: " + userProfile.getGender());
        System.out.println("University: " + userProfile.getUniversity());
        System.out.println("Department: " + userProfile.getDepartment());
        System.out.println("Year of Study: " + userProfile.getYearOfStudy());
        System.out.println("Interests: " + userProfile.getInterests());
        System.out.println("Volunteer Activities: " + userProfile.getVolunteerActivities());

        System.out.println("\nThank you for using the application!");

        System.out.println("\nWould you like to create an assignment? (yes/no):");
        String createAssignment = scanner.nextLine().trim().toLowerCase();

        if (createAssignment.equals("yes")) {
            // Δημιουργία Assignment
            Assignment assignment = Assignment.createAssignment();
            System.out.println("\n--- Assignment ---");
            System.out.println(assignment);
        } else {
            System.out.println("\nNo assignment created.");
        }
	}
	private static UserProfile createUserProfile(Scanner scanner) {
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

                // Επικύρωση προφίλ
                if (!ValidatorUserProfile.validate(userProfile)) {
                    System.out.println("Please complete the required fields correctly.");
                    continue;
                }

                // Αν το προφίλ είναι έγκυρο, βγες από το loop
                break;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
        return userProfile;
    }
}
