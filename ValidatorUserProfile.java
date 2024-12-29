import java.util.List;
public class ValidatorUserProfile {
    public static boolean validate(UserProfile userProfile) {
        StringBuilder errors = new StringBuilder();

        // Έλεγχος για υποχρεωτικά πεδία
        if (userProfile.getAge() == -1) {
            errors.append("Age is a required field.\n");
        }
        if (userProfile.getGender() == null || userProfile.getGender().isEmpty()) {
            errors.append("Gender is a required field.\n");
        }
        if (userProfile.getUniversity() == null || userProfile.getUniversity().isEmpty()) {
            errors.append("University is a required field.\n");
        }
        if (userProfile.getDepartment() == null || userProfile.getDepartment().isEmpty()) {
            errors.append("Department is a required field.\n");
        }
        if (userProfile.getYearOfStudy() == null || userProfile.getYearOfStudy().isEmpty()) {
            errors.append("Year of studies is a required field.\n");
        }

        // Εμφάνιση λαθών αν υπάρχουν
        if (errors.length() > 0) {
            System.out.println("Please make sure all the mandatory fields are completed correctly.");
            System.out.println(errors);
            return false;
        }
        return true; // Όλα τα υποχρεωτικά πεδία έχουν συμπληρωθεί
    }
}
