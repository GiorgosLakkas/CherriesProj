
/* 
public class MatchAlgoFriend {
    private  String[] User1 = new String[22];
    private String[] User2 = new String[22];

    
     public MatchAlgoFriend() {
        // Initialize User1 and User2 with default values
        FilterCriteria userinfo = new FilterCriteria(null, null, null, null);
        User1[0] = userinfo.getUniversityName();
        User1[1] = userinfo.getDepartment();
        User1[2] = String.valueOf(userinfo.getAge());
        User1[3] = userinfo.getGender();
}
    
    
    double matchinterest = 0;


    //Count the same interests the 2 users have
    public double  CalculateMatchInterest(String[] User1,String[] User2){
    for (int i=4;i<User1.length; i++){
        if (User1[i].equals(User2[i])){
            matchinterest++;
        }
    }
    
    matchinterest=matchinterest/18;
    return matchinterest;
    }

    //Adjust the percentage of the matched interests
    public double AdaptMatchInterest(double matchinterest){
        if (matchinterest<0.3){
            matchinterest=matchinterest-0.1*matchinterest;
        }else if(matchinterest<0.7){
            matchinterest=matchinterest+0.1*matchinterest;
        }else{
            matchinterest=matchinterest+0.2*matchinterest;
        }
        if (matchinterest>1.0){
            matchinterest=1.0;
        }
        if (matchinterest<0){
            matchinterest=0;
        }
        return matchinterest;
    }

    double matchbase = 0;

    //Check whether the users are from the same school and departemnt
    
    public double  CalculateMatchBase(String[] User1,String[] User2){
    if (User1[1].equals(User2[1]) && User1[2].equals(User2[2])){
        matchbase=1;
    }else if(User1[1].equals(User2[1])){
        matchbase=0.5;
    }else{
        matchbase=0;
    }
    return matchbase;
    }

    public double CalculateMatch(){
     double match=0.5*matchbase+0.5*matchinterest;
     return match;
    }
}
*/

import java.util.List;

public  class MatchAlgoFriend{
    public double compareUsers(User user1, User user2) {
        List<String> characteristics1 = user1.getCharacteristics();
        List<String> characteristics2 = user2.getCharacteristics();

        int matchingCharacteristics = 0;
        for (int i = 0; i < characteristics1.size(); i++) {
            if (characteristics1.get(i).equals(characteristics2.get(i))) {
                matchingCharacteristics++;
            }
        }

        double basePercentage = ((double) matchingCharacteristics / characteristics1.size()) * 100;
        double adjustedPercentage = adjustPercentage(basePercentage, matchingCharacteristics, characteristics1.size());

        return adjustedPercentage;
    }

    private double adjustPercentage(double basePercentage, int matchingCharacteristics, int totalCharacteristics) {
        double adjustmentFactor = matchingCharacteristics/220; // Adjustment factor to slightly increase or decrease the percentage
        if (matchingCharacteristics > totalCharacteristics / 2) {
            return basePercentage + adjustmentFactor * (matchingCharacteristics - totalCharacteristics / 2);
        } else {
            return basePercentage - adjustmentFactor * (totalCharacteristics / 2 - matchingCharacteristics);
        }
}