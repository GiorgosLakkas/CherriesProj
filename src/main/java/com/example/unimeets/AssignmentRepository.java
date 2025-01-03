package com.example.unimeets;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.unimeets.Assignment;


import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByDepartment(String department);
    List<Assignment> findBySemester(int semester);
    List<Assignment> findByCourse(String course);
    List<Assignment> findByRequiredMembers(int requiredMembers);
    List<Assignment> findByDepartmentAndSemester(String department, int semester);
    Optional<Assignment> findById(Long id);
    long countByDepartment(String department);
    long countBySemester(int semester);
}