import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SimpleMeetingSearch {

    private Connection databaseConnection;
    public SimpleMeetingSearch(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    public List<UserFilter> searchSimpleMeetingUsers() {
        List<UserFilter> users = new ArrayList<>();
        String query = "SELECT age, university, department_of_studies, sex, interests, volunteering_projects, academic_year " +
                       "FROM users " +
                       "WHERE purpose = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            statement.setString(1, "simple meeting");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer age = resultSet.getInt("age");
                    String university = resultSet.getString("university");
                    String departmentOfStudies = resultSet.getString("department_of_studies");
                    String sex = resultSet.getString("sex");
                    String interests = resultSet.getString("interests");
                    String volunteeringProjects = resultSet.getString("volunteering_projects");
                    String academicYear = resultSet.getString("academic_year");

                    UserFilter user = new UserFilter(age, university, departmentOfStudies, sex, interests, volunteeringProjects, academicYear);
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
