package com.example.unimeets;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchAlgoFriend {

    private final UserProfileRepository userProfileRepository;

    // Constructor to inject UserProfileRepository
    @Autowired
    public MatchAlgoFriend(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

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
    }    
    public List<FinalProduct> getUsersAbove95Match() {

        List<UserProfile> allUsers = userProfileRepository.findAll();
        List<String> highMatchUsers = new ArrayList<>();

        for (UserProfile otherUser : allUsers) {
            if (otherUser.getMyAppUser().getUsername().equals(User1[4])) {
                continue; 
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

            double matchInterest = CalculateMatchInterest(User1, User2);
            matchInterest = AdaptMatchInterest(matchInterest);
            double matchBase = CalculateMatchBase(User1, User2);
            double finalMatch = 0.5 * matchInterest + 0.5 * matchBase;

            if (finalMatch >= 0.95) {
                highMatchUsers.add(otherUser.getMyAppUser().getName() + " - Match: " + (finalMatch * 100) + "%");
            }
        }
        return highMatchUsers;
    }

    public double CalculateMatchInterest(String[] User1, String[] User2) {
        double matchInterest = 0;
        for (int i = 4; i < User1.length; i++) {
            if (User1[i] != null && User1[i].equals(User2[i])) {
                matchInterest++;
            }
        }
        return matchInterest / 18;
    }

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

    public double CalculateMatchBase(String[] User1, String[] User2) {
        if (User1[1].equals(User2[1]) && User1[2].equals(User2[2])) {
            return 1;
        } else if (User1[1].equals(User2[1])) {
            return 0.5;
        }
        return 0;
    }
}