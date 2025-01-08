package com.example.unimeets;
public class UserFilter {
    
    private String id; 
    private Integer age;
    private String university;
    private String departmentOfStudies;
    private String sex;
    private String interests;
    private String volunteeringProjects;
    private String academicYear;


    public UserFilter(String id, Integer age, String university, String departmentOfStudies, String sex, 
                      String interests, String volunteeringProjects, String academicYear) {
        this.id = id;
        this.age = age;
        this.university = university;
        this.departmentOfStudies = departmentOfStudies;
        this.sex = sex;
        this.interests = interests;
        this.volunteeringProjects = volunteeringProjects;
        this.academicYear = academicYear;
    }
    
    public String getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getUniversity() {
        return university;
    }

    public String getDepartmentOfStudies() {
        return departmentOfStudies;
    }

    public String getSex() {
        return sex;
    }

    public String getInterests() {
        return interests;
    }

    public String getVolunteeringProjects() {
        return volunteeringProjects;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    @Override
    public String toString() {
        return "UserFilter{" +
                "age=" + age +
                ", university='" + university + '\'' +
                ", departmentOfStudies='" + departmentOfStudies + '\'' +
                ", sex='" + sex + '\'' +
                ", interests='" + interests + '\'' +
                ", volunteeringProjects='" + volunteeringProjects + '\'' +
                ", academicYear='" + academicYear + '\'' +
                '}';
    }
}
