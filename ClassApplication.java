import java.util.List;

public class ClassApplication {
    
    private Database database;
    private MatchAlgorithm matchAlgorithm;
    private NotificationService notificationService;


    public ClassApplication() {
        this.database = new Database();
        this.matchAlgorithm = new MatchAlgorithm();
    }

    public void initializeUsers(List<User> users) {
        for (User user : users) {
            database.addUser(user);
        }
    }

    public List<User> applyFilters(FilterCriteria criteria) {
        List<User> filteredUsers = database.getUsersByCriteria(criteria);
        return filteredUsers;
    }
    
    
   public List<User> findBestMatches(FilterCriteria criteria) {
        List<User> filteredUsers = applyFilters(criteria);
        return matchAlgorithm.findBestMatches(filteredUsers, criteria);
    }

    public static void main(String[] args) {
        ClassApplication app = new ClassApplication();
        
        // Initialize with some users(dummies)
        List<User> users = List.of(
            new User("John", 20, "University A", "Department A", "Male", 2),
            new User("Jane", 22, "University B", "Department B", "Female",3)
            // Add more users
        );
        app.initializeUsers(users);
        
        // Create filter criteria
        FilterCriteria criteria = new FilterCriteria();
        criteria.setAge(20);
        criteria.setUniversity("University A");
        criteria.setDepartment("Department A");
        criteria.setGender("Male");
        criteria.setYearOfStudy(2);

        // Find best matches
        List<User> bestMatches = app.findBestMatches(criteria);
        
        // Display results
        for (User user : bestMatches) {
            System.out.println(user);
        }
    }
}
