package com.javawiz.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFrequencyOfEachWord {

    public static void main(String[] args) {
        String input = "apple banana apple orange banana apple";
        findWordFrequency(input);
    }

    private static void findWordFrequency(String input) {
        var words = input.split("\\W");
        Arrays.stream(words).collect(
            Collectors.groupingBy(Function.identity(), Collectors.counting())
        ).forEach(
            (key, value) -> System.out.println(key + ": " + value)
        );
    }
}
