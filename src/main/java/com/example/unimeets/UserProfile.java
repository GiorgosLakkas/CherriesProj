package com.example.unimeets;
import java.util.ArrayList;
import java.util.List;
public class UserProfile {
    private int age = -1;
    private String gender;
    private String university;
    private String department;
    private String yearOfStudy;
    private List<String> interests = new ArrayList<>();
    private List<String> volunteerActivities = new ArrayList<>();

    private int[] interestsMatrix = new int[0]; // Αρχικοποίηση με κενό πίνακα
    private int[] volunteerMatrix = new int[0]; // Αρχικοποίηση με κενό πίνακα

    public UserProfile() {}
    
    public void setAge(int age) {
        if (age < 17 || age > 100) {
            throw new IllegalArgumentException("Age should be between 17 and 100.");
        }
        this.age = age;  
    }

    public int getAge() {
        return this.age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUniversity() {
        return this.university;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getYearOfStudy() {
        return this.yearOfStudy;
    }

    public void setInterests(List<String> interests, List<String> allInterests) {
        this.interests = interests;
        this.interestsMatrix = generateBinaryMatrix(interests, allInterests);    }

    public List<String> getInterests() {
        return this.interests;
    }

    public void setVolunteerActivities(List<String> volunteerActivities, List<String> allActivities) {
        this.volunteerActivities = volunteerActivities;
        this.volunteerMatrix = generateBinaryMatrix(volunteerActivities, allActivities);
    }

    public List<String> getVolunteerActivities() {
        return this.volunteerActivities;
    }
    public int[] getInterestsMatrix() {
        return interestsMatrix;
    }

    public int[] getVolunteerMatrix() {
        return volunteerMatrix;
    }
     // Δημιουργία δυαδικού πίνακα
     private int[] generateBinaryMatrix(List<String> selected, List<String> allOptions) {
        int[] binaryMatrix = new int[allOptions.size()];
        if (selected == null || selected.isEmpty()) {
            // Αν η λίστα είναι null ή κενή, γεμίζουμε με 0
            return binaryMatrix; // Όλες οι θέσεις παραμένουν 0
        }
        for (int i = 0; i < allOptions.size(); i++) {
            binaryMatrix[i] = selected.contains(allOptions.get(i)) ? 1 : 0;
        }
        return binaryMatrix;
    }
}
