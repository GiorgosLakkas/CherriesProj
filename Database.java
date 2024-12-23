import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Database {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private MatchRepository matchRepository;

    // Method to add a user to the database
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to get a user by ID
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    // Method to find users by certain criteria (you can extend this to add filtering logic)
    public List<User> getUsersByCriteria(FilterCriteria criteria) {
        // You would implement filtering logic based on criteria here
        return userRepository.findAll();  // Simplified for now
    }

    // Method to add a university
    public University addUniversity(University university) {
        return universityRepository.save(university);
    }

    // Method to get a university by ID
    public Optional<University> getUniversityById(int universityId) {
        return universityRepository.findById(universityId);
    }

    // Method to get all universities
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    // Method to add a department
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Method to find best matches (for demonstration, you can use criteria)
    public List<User> findBestMatches(FilterCriteria criteria) {
        // You can add logic to find the best matches
        return getUsersByCriteria(criteria);  // Simplified
    }
}