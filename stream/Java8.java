package org.example.java8;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 3);
        List<Integer> numbersMedian = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<String> strings = Arrays.asList("apple", "potato", "banana", "orange", "grape", "chocolateMilk", "kiwi", "oai", "oia", "madam", "12321");
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        List<String> stringsWithNull = Arrays.asList("apple", null, "banana", null, "kiwi", "orange", null, "pear");
        List<String> stringsWithSpaces = Arrays.asList("app le", " banan a ", "ki wi ", "o rang  e ", "p ea r ");
        List<Integer> numbersRepeated = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        List<String> stringsSamelength = Arrays.asList("potato", "banana", "orange");
        List<String> stringsMaxVowel = Arrays.asList("potato", "banana", "orange");
        List<String> stringsPalindrome = Arrays.asList("potato", "banana", "orange", "banana", "potato");
        List<String> stringsWithNumeric = Arrays.asList("potat23o", "bana34na", "oran121ge", "ban34ana", "potat56o");
        List<Integer> kthLarge = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
       // * Write a program to find the sum of all elements in a list using Java Stream API.
         numbers2.stream().mapToInt(Integer::intValue).sum();

        // * Given a list of integers, write a program to find and print the maximum element using Java Stream API.
        numbers2.stream().mapToInt(Integer::intValue).max().getAsInt();

        // * Write a program to filter out all the even numbers from a list using Java Stream API.
         numbers2.stream().filter(e->(e%2==0)).collect(Collectors.toUnmodifiableList());
       //Given a list of strings, write a program to count the number of strings containing a specific character ‘a’ using Java Stream API.
        strings.stream().filter(e->e.contains("a")).collect(Collectors.toUnmodifiableList());

// * Write a program to convert a list of strings to uppercase using Java Stream API.
        strings.stream().map(String::toUpperCase).collect(Collectors.toUnmodifiableList());

        // * Given a list of integers, write a program to calculate the average of all the numbers using Java Stream API.
        numbers2.stream().mapToInt(Integer::intValue).average();

             //* Write a program to sort a list of strings in alphabetical order using Java Stream API.
        List<String> sortedNames =   strings.stream().sorted().collect(Collectors.toUnmodifiableList());
        // * Given a list of strings, write a program to concatenate all the strings using Java Stream API.
        String joined =strings.stream().collect(Collectors.joining());
        // * Write a program to find the longest string in a list of strings using Java Stream API.
      String maxS=  strings.stream().max(Comparator.comparingInt(String::length)).get();

      // * Given a list of integers, write a program to find and print the second largest number using Java Stream API.
       numbers2.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
               // * Write a program to remove all the duplicate elements from a list using Java Stream API.

        strings.stream().distinct().collect(Collectors.toUnmodifiableList());
                // * Given a list of strings, write a program to find and print the shortest string using Java Stream API.

        strings.stream().sorted().findFirst().get();
       // * Write a program to convert a list of integers to a list of their squares using Java Stream API.

       numbers2.stream().map(e->e*e).collect(Collectors.toUnmodifiableList());

       // * Given a list of strings, write a program to find and print the strings starting with a specific prefix ‘a’ using Java Stream API.
        strings.stream().filter(e->e.startsWith("a")).collect(Collectors.toUnmodifiableList());

                // * Write a program to find the product of all elements in a list of integers using Java Stream API.

        int o = numbers2.stream().reduce(1,(a,b)->a*b);
        System.out.println(o);
        // * Given a list of integers, write a program to find and print the prime numbers using Java Stream API.

    numbers2.stream().filter(Java8::isPrime).collect(Collectors.toUnmodifiableList());

// * Write a program to check if a list of strings contains a specific string using Java Stream API.
        strings.stream().filter(e->e.contains("asda")).collect(Collectors.toUnmodifiableList());

    // * Given a list of strings, write a program to find and print the strings with length greater than a specified value (5) using Java Stream API.
    // *20 Write a program to filter out all the elements divisible by 3 and 5 from a list of integers using Java Stream API.
        Predicate<Integer> p = n->(n%3==0 || n%5==0);
        numbers2.stream().filter(p).collect(Collectors.toUnmodifiableList());
        // * Given a list of strings, write a program to find and print the strings with the maximum length using Java Stream API.
        int max = strings.stream().mapToInt(String::length).max().getAsInt();
        strings.stream().filter(e->e.length()==max).collect(Collectors.toUnmodifiableList());

        // * Write a program to reverse a list of strings using Java Stream API.
        strings.stream().map(e->new StringBuilder(e).reverse()).collect(Collectors.toUnmodifiableList());
        // * Given a list of integers, write a program to find and print the distinct odd numbers using Java Stream API.
        numbers2.stream().distinct().filter(e->(e%2==1)).collect(Collectors.toUnmodifiableList());
        // * Write a program to remove all null values from a list of strings using Java Stream API.
        strings.stream().filter(e->e.toLowerCase().chars().allMatch(c->"aeiou".indexOf(c)!=-1)).collect(Collectors.toUnmodifiableList());
// * Write a program to convert a list of strings to a comma-separated string using Java Stream API.

        strings.stream().collect(Collectors.joining(","));
        // * Given a list of integers, write a program to find and print the index of the first occurrence of a specific number using Java Stream API.
       int target = 20;
        numbers2.stream().filter(e->e==target).findFirst().get();

        // * Given a list of strings, write a program to find and print the strings containing duplicate characters using Java Stream API.

        // * Given a list of strings, write a program to find and print the strings with the minimum length using Java Stream API.

        int min = strings.stream().mapToInt(String::length).min().getAsInt();
        strings.stream().filter(str->str.length()==min).collect(Collectors.toUnmodifiableList());
// * Write a program to find the frequency of each element in a list of integers using Java Stream API.

        strings.stream().collect(Collectors.groupingBy(n->n,Collectors.counting()));
        boolean result = numbers2.stream().sorted().equals(numbers2);
        //[1,1,2,2,
        // * 50Given a list of integers, write a program to find and print the elements with the lowest frequency using Java Stream API.
       Set<Integer> n = numbers2.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet()
                .stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toSet());

       //find first non repeating charachter
       String name = "pintu";
       name.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
               .entrySet().stream().filter(e->e.getValue()==1).map(Map.Entry::getKey).findFirst().get();
       List<String> strArr = Arrays.asList("1,2,3", "3,4,5", "6,abc,7", "8,9,10", "10,2,4");

        strArr.stream().flatMap(s->
                Arrays.stream(s.split("\\,"))).filter(e->e.matches("\\d+")).distinct().collect(Collectors.toUnmodifiableList());
// * 50Given a list of integers, write a program to find and print the elements with the lowest frequency using Java Stream API.
        /*numbers2.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet()
                .stream().sorted(Comparator.comparing(Map.Entry::getValue)).*/
        // * Given a list of strings, write a program to find and print the strings containing only digits using Java Stream API.
        strings.stream().filter(e->e.matches("\\d+")).collect(Collectors.toUnmodifiableList())
// * Write a program to check if a list of strings is palindrome using Java Stream API.
        strings.stream().forEach(e->{
            if (new StringBuilder(e).reverse().toString().equalsIgnoreCase(e)) {

            }
        });
    }

    public static boolean isPrime(int num){
        if(num<=1){
            return false;
        }
        for (int i = 2; i < Math.sqrt(num) ; i++) {
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}
