package com.javawiz.stream;

import java.util.Arrays;
import java.util.Comparator;

public class GetLongestShortestWords {

    public static void main(String[] args) {
        String input = "apple banana kiwi orange fig strawberry";
        getLongestAndShortestWords(input);
        java8Way(input);
    }

    private static void java8Way(String input) {
        var words = input.split("\\W+");

        String longestWord = Arrays.stream(words)
                .reduce("", (a, b) -> a.length() >= b.length() ? a : b);

        String shortestWord = Arrays.stream(words)
                .reduce(words[0], (a, b) -> a.length() <= b.length() ? a : b);

        System.out.println("Longest word (Java 8 way): " + longestWord);
        System.out.println("Shortest word (Java 8 way): " + shortestWord);

        String max = Arrays.stream(words).max(Comparator.comparing(String::length)).get();
        String min = Arrays.stream(words).min(Comparator.comparing(String::length)).get();
        System.out.println("Longest word (Java 8 way - max): " + max);
        System.out.println("Shortest word (Java 8 way - min): " + min);
    }

    private static void getLongestAndShortestWords(String input) {
        var words = input.split("\\W+");

        String longestWord = "";
        String shortestWord = words[0];

        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
            if (word.length() < shortestWord.length()) {
                shortestWord = word;
            }
        }

        System.out.println("Longest word: " + longestWord);
        System.out.println("Shortest word: " + shortestWord);
    }
}
