import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class UserSelection {
    public UserSelection(){
    }
    // Μέθοδος για τη λήψη των επιλογών ηλικίας
    public static List<Integer> getAgeOptions() {
        return IntStream.rangeClosed(17, 100).boxed().collect(Collectors.toList());
    }
    private static final List<String> genderOptions = List.of("Male", "Female", "Other");
    private static final List<String> universityOptions = List.of("Athens University of Economics and Business");
    private static final List<String> departmentOptions = List.of("International and European Economic Studies", "Economics", "Management Science and Technology", "Businesss Administration", "Accounting and Finance", "Marketing and Communication", "Informatics", "Statistics");
    private static final List<String> yearOptions = List.of("1st", "2nd", "3rd", "4th", "5th", "6th+");
    private static final List<String> interestsOptions = List.of("Sports", "Gastronomy", "Yoga/Meditation", "Fitness", "Science", "History", "Volunteering", "Literature and Writing", "Online Hobbies/Social Media", "Scale Modeling/Modeling Building", "Music", "Foreign Languages", "Games and Entertainment", "Series/Movies", "Travelling", "Arts", "Technology", "Dance");
    private static final List<String> volunteerActivitiesOptions = List.of("AIESEC", "Acein aueb", "ThinkBiz");
    
    public static List<String> getGenderOptions() {
        return genderOptions;
    }
    // Μέθοδος για τη λήψη των επιλογών πανεπιστημίου
    public static List<String> getUniversityOptions() {
        return universityOptions;
    }
    // Μέθοδος για τη λήψη των επιλογών τμήματος
    public static List<String> getDepartmentOptions() {
        return departmentOptions;
    }
    // Μέθοδος για τη λήψη των επιλογών έτους σπουδών
    public static List<String> getYearOptions() {
        return yearOptions;
    }
    // Μέθοδος για τη λήψη των επιλογών ενδιαφερόντων
    public static List<String> getInterestsOptions() {
        return interestsOptions;
    }
    // Μέθοδος για τη λήψη των επιλογών εθελοντικής δράσης
    public static List<String> getVolunteerActivitiesOptions() {
        return volunteerActivitiesOptions;
    }   
    //  μέθοδος για μονή επιλογή
    public static <T> T getUserSelection(Scanner scanner, List<T> options, String prompt) {
            System.out.println(prompt + ": ");
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Insert the number of your choice: ");
    
            int choice = -1;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 1 && choice <= options.size()) {
                        break;
                    } else {
                        System.out.print("Wrong Choice. Please choose number between 1 and " + options.size() + ": ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid Input. Please insert a number: ");
                }
            }
            return options.get(choice - 1);
    }
    // Μέθοδος για πολλαπλές επιλογές
    public static <T> List<T> getMultipleUserSelections(Scanner scanner, List<T> options, String prompt, int maxSelections) {
        System.out.println(prompt + ": ");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.println("Insert the numbers of your choices, divided by comma (e.g. 1,2,3) or leave blank for no selection: ");
        System.out.println("You can select up to " + maxSelections + " options.");

        List<T> selectedOptions = new ArrayList<>();
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return selectedOptions; // Καμία επιλογή
        }

        try {
            String[] indices = input.split(",");
            if (indices.length > maxSelections) {
                System.out.println("You can only select up to " + maxSelections + " options. Try again.");
                return getMultipleUserSelections(scanner, options, prompt, maxSelections);
            }
    
            for (String index : indices) {
                int choice = Integer.parseInt(index.trim());
                if (choice >= 1 && choice <= options.size()) {
                    selectedOptions.add(options.get(choice - 1));
                } else {
                    System.out.println("The choice " + choice + " is invalid and ignored.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again.");
            return getMultipleUserSelections(scanner, options, prompt, maxSelections);
        }    

        return selectedOptions;
    }
}
    