package com.example.unimeets;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;

public class EventNameMatcher {
    public static void main(String[] args) {
        String name1 = "Annual Tech Conference 2024";
        String name2 = "annual tech conference";

        // Normalize the strings
        name1 = name1.toLowerCase().trim();
        name2 = name2.toLowerCase().trim();

        // Calculate Levenshtein Distance
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        int distance = levenshtein.apply(name1, name2);
        int maxLength = Math.max(name1.length(), name2.length());
        double levenshteinSimilarity = (1 - ((double) distance / maxLength)) * 100;

        // Calculate Jaro-Winkler Similarity
        JaroWinklerSimilarity jaroWinkler = new JaroWinklerSimilarity();
        double jaroWinklerSimilarity = jaroWinkler.apply(name1, name2) * 100;

        // Combine the results (you can adjust the weights as needed)
        double finalSimilarity = (levenshteinSimilarity + jaroWinklerSimilarity) / 2;

        System.out.println("Levenshtein Similarity: " + levenshteinSimilarity + "%");
        System.out.println("Jaro-Winkler Similarity: " + jaroWinklerSimilarity + "%");
        System.out.println("Final Similarity: " + finalSimilarity + "%");
    }
}
