package com.example.unimeets;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

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

    @Lob
    @Column(name = "interests_matrix")
    private byte[] interestsMatrix;  // Store as binary data

    @Lob
    @Column(name = "volunteer_matrix")
    private byte[] volunteerMatrix;  // Store as binary data

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
        this.interestsMatrix = generateBinaryMatrix(interests, allInterests);  // Generate binary matrix and set
    }

    public List<String> getInterests() {
        return this.interests;
    }

    public void setVolunteerActivities(List<String> volunteerActivities, List<String> allActivities) {
        this.volunteerActivities = volunteerActivities;
        this.volunteerMatrix = generateBinaryMatrix(volunteerActivities, allActivities);  // Generate binary matrix and set
    }

    public List<String> getVolunteerActivities() {
        return this.volunteerActivities;
    }

    public byte[] getInterestsMatrix() {
        return interestsMatrix;
    }

    public byte[] getVolunteerMatrix() {
        return volunteerMatrix;
    }

    private byte[] generateBinaryMatrix(List<String> selectedItems, List<String> allItems) {
        List<Integer> matrixList = new ArrayList<>();
        for (String item : allItems) {
            matrixList.add(selectedItems.contains(item) ? 1 : 0);
        }

        // Convert List<Integer> to byte[] (this is a simple way, consider your data structure)
        return serializeMatrix(matrixList);
    }

    private byte[] serializeMatrix(List<Integer> matrixList) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(matrixList);
            return bos.toByteArray();  // Return byte array
        } catch (IOException e) {
            throw new RuntimeException("Error serializing matrix", e);
        }
    }
}
