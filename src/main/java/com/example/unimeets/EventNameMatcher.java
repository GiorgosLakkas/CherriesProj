package com.example.unimeets;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;

public class EventNameMatcher {
    
        public double getPercentage(String name1, String name2) {

            System.out.println("Comparing: '" + name1 + "' with '" + name2 + "'");
            if (name1 == null || name2 == null || name1.trim().isEmpty() || name2.trim().isEmpty()) {
                System.out.println("One or both names are null. Returning similarity as 0.");
                return 0.0;  // Return 0 similarity if any name is null
            }
        // Normalize the strings
        name1 = name1.toLowerCase().trim();
        name2 = name2.toLowerCase().trim();

        // Calculate Levenshtein Distance
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        int distance = levenshtein.apply(name1, name2);
        int maxLength = Math.max(name1.length(), name2.length());

        double levenshteinSimilarity = 0.0;
        if (maxLength > 0) {
            levenshteinSimilarity = (1 - ((double) distance / maxLength)) * 100;
        } else {
            System.out.println("Both names are empty. Returning similarity as 0%.");
            return 0.0;
        }


        // Calculate Jaro-Winkler Similarity
        JaroWinklerSimilarity jaroWinkler = new JaroWinklerSimilarity();
        double jaroWinklerSimilarity = jaroWinkler.apply(name1, name2) * 100;

        // Combine the results (you can adjust the weights as needed)
        double finalSimilarity = (levenshteinSimilarity + jaroWinklerSimilarity) / 2;

        System.out.println("Levenshtein Similarity: " + levenshteinSimilarity + "%");
        System.out.println("Jaro-Winkler Similarity: " + jaroWinklerSimilarity + "%");
        System.out.println("Final Similarity: " + finalSimilarity + "%");

        return finalSimilarity;
    }
}

