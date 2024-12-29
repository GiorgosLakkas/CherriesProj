package com.example.unimeets;

import java.util.*;
public class Assignment {
    private String department;  // π.χ., ΔΕΤ, ΟΔΕ
    private int semester;       // π.χ., 1ο, 2ο κτλ.
    private String course;      // π.χ., Προγραμματισμός ΙΙ
    private int requiredMembers; // Αριθμός ατόμων που χρειάζονται

    // Λίστες επιλογών
    private static final List<String> DEPARTMENTS = List.of("International and European Economic Studies", "Economics", "Management Science and Technology", "Businesss Administration", "Accounting and Finance", "Marketing and Communication", "Informatics", "Statistics");
    private static final List<Integer> SEMESTERS = List.of(1, 2, 3, 4, 5, 6, 7, 8);

    // Κατασκευαστής
    public Assignment(String department, int semester, String course, int requiredMembers) {
        this.department = department;
        this.semester = semester;
        this.course = course;
        this.requiredMembers = requiredMembers;
    }

    // Getters
    public String getDepartment() {
        return department;
    }

    public int getSemester() {
        return semester;
    }

    public String getCourse() {
        return course;
    }

    public int getRequiredMembers() {
        return requiredMembers;
    }

    // Setters
    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setRequiredMembers(int requiredMembers) {
        this.requiredMembers = requiredMembers;
    }

    // Μέθοδος για την είσοδο δεδομένων
    public static Assignment createAssignment() {
        try (Scanner scanner = new Scanner(System.in)) {
            String department = "";
            int semester = 0;
            String course = "";
            int requiredMembers = 0;
   
            // Επιλογή τμήματος
            while (true) {
                try {
                    System.out.println("Please choose department:");
                    for (int i = 0; i < DEPARTMENTS.size(); i++) {
                        System.out.println((i + 1) + ". " + DEPARTMENTS.get(i));
                    }
                    int deptChoice = Integer.parseInt(scanner.nextLine());
                    if (deptChoice < 1 || deptChoice > DEPARTMENTS.size()) {
                        throw new IllegalArgumentException("Invalid department choice.");
                    }
                    department = DEPARTMENTS.get(deptChoice - 1);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number corresponding to the department.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid choice. " + e.getMessage());
                }
            }
   
            // Επιλογή εξαμήνου
            while (true) {
                try {
                    System.out.println("Please choose semester (1-8):");
                    int semChoice = Integer.parseInt(scanner.nextLine());
                    if (!SEMESTERS.contains(semChoice)) {
                        throw new IllegalArgumentException("Invalid semester choice.");
                    }
                    semester = semChoice;
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number (1-8).");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid choice. " + e.getMessage());
                }
            }
   
            // Εισαγωγή μαθήματος
            while (true) {
                try {
                    System.out.println("Please enter course:");
                    course = scanner.nextLine().trim();
                    if (course.isEmpty()) {
                        throw new IllegalArgumentException("Course name cannot be empty.");
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input. " + e.getMessage());
                }
            }
   
            // Εισαγωγή αριθμού συμμετεχόντων
            while (true) {
                try {
                    System.out.println("Please enter the number of people needed for the assignment:");
                    requiredMembers = Integer.parseInt(scanner.nextLine());
                    if (requiredMembers <= 0) {
                        throw new IllegalArgumentException("Number of participants must be greater than 0.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a positive integer for the number of participants.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid choice. " + e.getMessage());
                }
            }
   
            return new Assignment(department, semester, course, requiredMembers);
        }
    }
    
    

    @Override
    public String toString() {
        return "Assignment{" +
                "department='" + department + '\'' +
                ", semester=" + semester +
                ", course='" + course + '\'' +
                ", requiredMembers=" + requiredMembers +
                '}';
    }
}
