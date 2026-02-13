package com.javawiz.stream;

import java.util.Arrays;

public class FindMedian {

    public static void main(String[] args) {
        int[] numbers = {5, 3, 8, 1, 4, 7, 6, 2};

        double median = findMedian(numbers);
        System.out.println("Median: " + median);
    }

    private static double findMedian(int[] numbers) {
        Arrays.sort(numbers);
        int length = numbers.length;

        if (length % 2 == 0) {
            return (numbers[length / 2 - 1] + numbers[length / 2]) / 2.0;
        } else {
            return numbers[length / 2];
        }
    }
}
