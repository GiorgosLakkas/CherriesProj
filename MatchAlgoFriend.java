import java.util.ArrayList;
import java.util.List;

public class MatchAlgoFriend {
    private List<String> User1 = new ArrayList<>(22);
    private List<String> User2 = new ArrayList<>(22);

    public MatchAlgoFriend() {
        // Initialize User1 and User2 with default values
        FilterCriteria userinfo = new FilterCriteria(null, null, null, null);
        User1.add(userinfo.getUniversityName());
        User1.add(userinfo.getDepartment());
        User1.add(String.valueOf(userinfo.getAge()));
        User1.add(userinfo.getGender());
        // Fill remaining positions with empty strings
        for (int i = 4; i < 22; i++) {
            User1.add("");
            User2.add("");
        }
    }

    double matchinterest = 0;

    // Count the same interests the 2 users have
    public double CalculateMatchInterest(List<String> User1, List<String> User2) {
        for (int i = 4; i < User1.size(); i++) {
            if (User1.get(i).equals(User2.get(i))) {
                matchinterest++;
            }
        }

        matchinterest = matchinterest / 18;
        return matchinterest;
    }

    // Adjust the percentage of the matched interests
    public double AdaptMatchInterest(double matchinterest) {
        if (matchinterest < 0.3) {
            matchinterest = matchinterest - 0.1 * matchinterest;
        } else if (matchinterest < 0.7) {
            matchinterest = matchinterest + 0.1 * matchinterest;
        } else {
            matchinterest = matchinterest + 0.2 * matchinterest;
        }
        if (matchinterest > 1.0) {
            matchinterest = 1.0;
        }
        if (matchinterest < 0) {
            matchinterest = 0;
        }
        return matchinterest;
    }

    double matchbase = 0;

    // Check whether the users are from the same school and department
    public double CalculateMatchBase(List<String> User1, List<String> User2) {
        if (User1.get(1).equals(User2.get(1)) && User1.get(2).equals(User2.get(2))) {
            matchbase = 1;
        } else if (User1.get(1).equals(User2.get(1))) {
            matchbase = 0.5;
        } else {
            matchbase = 0;
        }
        return matchbase;
    }

    public double CalculateMatch() {
        double match = 0.5 * matchbase + 0.5 * matchinterest;
        return match;
    }
}
