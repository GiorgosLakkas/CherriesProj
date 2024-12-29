package com.example.unimeets;
public class FilterCriteria {
     private Integer age;
     private String gender;
     private String universityName;
     private String department;

    public FilterCriteria(Integer age, String gender, String universityName, String department) {
        this.age = age;
        this.gender = gender;
        this.universityName = universityName;
        this.department = department;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Age: " + age + ", Gender: " + gender + ", University: " + universityName + ", Department: " + department;
    }
}

