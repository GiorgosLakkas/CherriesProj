package com.example.unimeets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MatchAlgoFriend {

    private final UserProfileRepository userProfileRepository;

    // Constructor to inject UserProfileRepository
    @Autowired
    public MatchAlgoFriend(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Transactional
    public List<String> getUsersAbove95Match(UserProfile userProfile) {
        String[] User1 = new String[22];

        // Initialize User1 array with values from userProfile
        User1[0] = userProfile.getUniversity();
        User1[1] = userProfile.getDepartment();
        User1[2] = String.valueOf(userProfile.getAge());
        User1[3] = userProfile.getGender();
        List<String> interests = userProfile.getInterests();
        for (int i = 0; i < interests.size(); i++) {
            User1[4 + i] = interests.get(i);
        }

        List<UserProfile> allUsers = userProfileRepository.findAll();
        List<String> highMatchUsers = new ArrayList<>();

        for (UserProfile otherUser : allUsers) {
            if (otherUser.getMyAppUser().getUsername().equals(userProfile.getMyAppUser().getId())) {
                continue; // Skip comparing the user with themselves
            }

            String[] User2 = new String[22];
            User2[0] = otherUser.getUniversity();
            User2[1] = otherUser.getDepartment();
            User2[2] = String.valueOf(otherUser.getAge());
            User2[3] = otherUser.getGender();
            List<String> interestsOtherUser = otherUser.getInterests();
            for (int i = 0; i < interestsOtherUser.size(); i++) {
                User2[4 + i] = interestsOtherUser.get(i);
            }

            // Calculate match interest and base
            double matchInterest = CalculateMatchInterest(User1, User2);
            matchInterest = AdaptMatchInterest(matchInterest);
            double matchBase = CalculateMatchBase(User1, User2);
            double finalMatch = 0.5 * matchInterest + 0.5 * matchBase;

            // If match is above 95%, add user's name to the list
            if (finalMatch >= 0.95) {
                highMatchUsers.add(otherUser.getMyAppUser().getName() + " (" + finalMatch + ")");
            }
        }

        // Sort the matching users based on the percentage in descending order
        highMatchUsers.sort((user1, user2) -> {
            double match1 = Double.parseDouble(user1.split(" \\(")[1].replace(")", ""));
            double match2 = Double.parseDouble(user2.split(" \\(")[1].replace(")", ""));
            return Double.compare(match2, match1);
        });

        return highMatchUsers;
    }

      // Method to calculate interest match
      public double CalculateMatchInterest(String[] User1, String[] User2) {
        double matchInterest = 0;
        int totalInterests = 0;
        for (int i = 4; i < User1.length; i++) {
            if (User1[i] != null && User2[i] != null) {
                totalInterests++;
                if (User1[i] != null && User1[i].equals(User2[i])) {
                    matchInterest++;
                }
            }
        }
        if ( totalInterests == 0) {
            return 0;
        }
        return matchInterest / totalInterests;
    }


    // Adapt match interest based on thresholds
    public double AdaptMatchInterest(double matchInterest) {
        if (matchInterest < 0.3) {
            matchInterest -= 0.1 * matchInterest;
        } else if (matchInterest < 0.7) {
            matchInterest += 0.1 * matchInterest;
        } else {
            matchInterest += 0.2 * matchInterest;
        }
        return Math.min(1.0, Math.max(0, matchInterest));
    }

    // Method to calculate base match based on department and age
    public double CalculateMatchBase(String[] User1, String[] User2) {
        if (User1[1].equals(User2[1]) && User1[2].equals(User2[2])) {
            return 1;
        } else if (User1[1].equals(User2[1])) {
            return 0.5;
        }
        return 0;
    }
}