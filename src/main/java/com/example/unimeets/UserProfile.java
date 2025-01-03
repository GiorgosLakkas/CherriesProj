package com.example.unimeets;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserProfile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String university;

    @Column(nullable = false)
    private String department;

    @Column(name = "year_of_study", nullable = false)
    private String yearOfStudy;

    @ElementCollection
    @CollectionTable(name = "InterestsMatrix", joinColumns = @JoinColumn(name = "user_profile_id"))
    @Column(name = "value")
    private List<int[]> interestsMatrix = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "VolunteerMatrix", joinColumns = @JoinColumn(name = "user_profile_id"))
    @Column(name = "value")
    private List<int[]> volunteerMatrix = new ArrayList<>();

    @ElementCollection
    private List<String> interests = new ArrayList<>();

    @ElementCollection
    private List<String> volunteerActivities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "myappuser_id")
    private MyAppUser myAppUser;

    // Other methods

    public void setMyAppUser(MyAppUser myAppUser) {
        this.myAppUser = myAppUser;
    }

    public MyAppUser getMyAppUser() {
        return myAppUser;
    }

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
    public List<int[]> getInterestsMatrix() {
        return interestsMatrix;
    }

    public List<int[]> getVolunteerMatrix() {
        return volunteerMatrix;
    }
     // Δημιουργία δυαδικού πίνακα
    private List<int[]> generateBinaryMatrix(List<String> selectedItems, List<String> allItems) {
        List<int[]> matrix = new ArrayList<>();
        for (String item : allItems) {
            int[] row = new int[1]; // You might want to use a more complex structure depending on your needs.
            row[0] = selectedItems.contains(item) ? 1 : 0;
            matrix.add(row);
        }
        return matrix;
    }
}
