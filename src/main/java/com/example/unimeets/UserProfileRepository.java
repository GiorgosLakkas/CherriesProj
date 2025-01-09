package com.example.unimeets;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    // Find a UserProfile by the associated MyAppUser ID (Assuming a relation between UserProfile and MyAppUser)
    Optional<UserProfile> findByMyAppUserId(Long myAppUserId);

    // Find all UserProfiles by University
    List<UserProfile> findByUniversity(String university);

    // Find all UserProfiles by Department
    List<UserProfile> findByDepartment(String department);

    // Find all UserProfiles by Year of Study
    List<UserProfile> findByYearOfStudy(String yearOfStudy);

    // Find all UserProfiles by Age range
    List<UserProfile> findByAgeBetween(int startAge, int endAge);

    // Find UserProfile by Gender
    List<UserProfile> findByGender(String gender);

    // Find UserProfiles by specific interest (Assuming you want to search by interest as a string)
    List<UserProfile> findByInterestsContains(String interests);

    // You can also create more complex queries, for example:
    // Find UserProfiles by University and Department
    List<UserProfile> findByUniversityAndDepartment(String university, String department);
}