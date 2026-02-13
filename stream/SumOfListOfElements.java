package com.javawiz.stream;

import java.util.List;

public class SumOfListOfElements {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Long> long_list = List.of(10000000000L, 20000000000L, 30000000000L);
        sumLongList(long_list);

        System.out.println("Sum using for loop: " + sumUsingForLoop(list));
        System.out.println("Sum using streams: " + sumUsingStreams(list));
    }

    private static void sumLongList(List<Long> longList) {
        Long sum = longList.stream().reduce(0L, Long::sum);
        System.out.println("Sum of long list using streams: " + sum);
    }

    private static String sumUsingForLoop(List<Integer> list) {
        int sum = 0;
        for (Integer num : list) {
            sum += num;
        }
        return String.valueOf(sum);
    }

    private static Integer sumUsingStreams(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }
}
