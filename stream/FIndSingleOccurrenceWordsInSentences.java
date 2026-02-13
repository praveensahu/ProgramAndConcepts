package com.javawiz.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FIndSingleOccurrenceWordsInSentences {

    public static void main(String[] args) {
        List<String> sentences = List.of("Java is a programming language",
                                         "Streams are a new feature in Java",
                                         "Java streams provide a modern way to process data");

        extracted(sentences);
        System.out.println("----");
        extracted3(sentences);
    }

    // Implementation to find words that occur only once across all sentences
    private static void extracted(List<String> sentences) {
        sentences.stream()
            .map(sentence -> Arrays.stream(sentence.split("\\W")).toList())
            .flatMap(List::stream)
            .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Entry::getKey)
            .forEach(System.out::println);
    }
    // Alternative implementation
    private static void extracted3(List<String> sentences) {
        List<String> words = sentences.stream()
            .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Entry::getKey)
            .toList();
        words.forEach(System.out::println);
    }
}
