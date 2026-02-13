package com.javawiz.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindMostRepeatedWordInASentence {

    public static void main(String[] args) {
        String sentence = "Java is great and Java is object oriented and Java is platform independent";
        findMostRepeatedWords(sentence);
    }

    private static void findMostRepeatedWords(String sentence){
        var mostRepeatedWord = Arrays.stream(sentence.split("\\W"))
            .collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()
            ));
        // Find all words with the maximum count
        List<String> mostRepeatedWords = mostRepeatedWord.entrySet().stream()
            .filter(e -> e.getValue().equals(Collections.max(mostRepeatedWord.values())))
            .map(Map.Entry::getKey)
            .toList();
        System.out.println("Most repeated words: " + mostRepeatedWords);
    }

    // Alternative approach to find a single most repeated word
    // as max returned the first encountered maximum word in case of ties.
    private static void findMostRepeatedWord(String sentence) {
        var wordCount = sentence.split(" ");
        var mostRepeatedWord = Arrays.stream(wordCount)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Entry.comparingByValue())
                .map(Entry::getKey)
                .orElse("No words found");

        System.out.println("Most repeated word: " + mostRepeatedWord);
    }
}
