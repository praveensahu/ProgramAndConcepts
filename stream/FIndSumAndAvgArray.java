package com.javawiz.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class FIndSumAndAvgArray {

    public static void main(String[] args) {
        int[] numbers = {5, 3, 8, 1, 4, 7, 6, 2};

        System.out.println("Sum: " + findSum(numbers));
        System.out.println("Average: " + findAverage(numbers));

        System.out.println("Sum: " + Arrays.stream(numbers).sum());
        System.out.println("Average: " + Arrays.stream(numbers).average().orElse(0.0));

        System.out.println("Sum: " + IntStream.of(numbers).sum());
        System.out.println("Average: " + IntStream.of(numbers).average().orElse(0.0));

        IntSummaryStatistics stats = Arrays.stream(numbers).summaryStatistics();
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Average: " + stats.getAverage());
    }

    private static int findSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    private static double findAverage(int[] numbers) {
        return (double) findSum(numbers) / numbers.length;
    }
}
