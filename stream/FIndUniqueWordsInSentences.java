package com.javawiz.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FIndUniqueWordsInSentences {
    public static void main(String[] args) {
        List<String> sentences = List.of("Java is a programming language",
                                          "Streams are a new feature in Java",
                                          "Java streams provide a modern way to process data");
        extracted(sentences);
        System.out.println("----");
        extracted2(sentences);
        System.out.println("----");

        String text = "Java is a programming language. Streams are a new feature in Java. Java streams provide a modern way to process data.";
        Arrays.stream(text.split(" ")).distinct().forEach(System.out::println);
    }

    private static void extracted(List<String> sentences) {
        sentences.stream()
            .map(sentence -> sentence.split(" ")) // Split each sentence into words
            .flatMap(Arrays::stream)              // Flatten the stream of word arrays into a stream of words
            .distinct()                           // Get unique words
            .forEach(System.out::println);        // Print each unique word
    }

    private static void extracted2(List<String> sentences) {
        sentences.stream()
            .flatMap(sentence -> Arrays.stream(sentence.split(" "))) // Split and flatten in one step
            .distinct()                                             // Get unique words
            .forEach(System.out::println);                          // Print each unique word
    }
}
