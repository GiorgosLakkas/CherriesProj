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

    public void setInterests(List<String> interests) {
        this.interests = new ArrayList<>(interests);
    }

    public List<String> getInterests() {
        return this.interests;
    }

    public void setVolunteerActivities(List<String> volunteerActivities) {
        this.volunteerActivities = new ArrayList<>(volunteerActivities);
    }

    public List<String> getVolunteerActivities() {
        return this.volunteerActivities;
    }
}
