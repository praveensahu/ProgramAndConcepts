package com.javawiz.stream;

import com.javawiz.model.Student;
import com.javawiz.model.Subject;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HighestAvgMarkStudent {

    public static void main(String[] args) {
        List<Student> students = Student.getStudents();
        lagacy(students);
        newWay(students);
        anotherWay(students);
    }

    private static void lagacy(List<Student> students) {
        Student highestAvgMarkStudent = null;
        double highestAvgMark = 0.0;

        for (Student student : students) {
            double totalMarks = 0.0;
            for (Subject subject : student.subjects()) {
                totalMarks += subject.totalMark();
            }
            double avgMark = totalMarks / student.subjects().size();

            if (avgMark > highestAvgMark) {
                highestAvgMark = avgMark;
                highestAvgMarkStudent = student;
            }
        }

        if (highestAvgMarkStudent != null) {
            System.out.println("Student with highest average marks: " + highestAvgMarkStudent.name() +
                                   " with average marks: " + highestAvgMark);
        }
    }

    private static void newWay(List<Student> students) {
        students.stream().collect(Collectors.groupingBy(
                Student::name,
                Collectors.averagingDouble(student -> student.subjects().stream().mapToDouble(Subject::totalMark).average().orElse(0.0))
            )).entrySet().stream()
            .max(Comparator.comparingDouble(Entry::getValue))
            .ifPresent(e -> System.out.println("Student with highest average marks: " + e.getKey() + " with average marks: " + e.getValue()));
    }

    private static void anotherWay(List<Student> students) {
        students.stream()
            .collect(
                Collectors.groupingBy(
                    Function.identity(),
                    Collectors.averagingDouble(
                        student -> student.subjects().stream().mapToDouble(Subject::totalMark).average().orElse(0.0)
                    )
                )
            )
            .entrySet().stream()
            .max(Entry.comparingByValue())
            .ifPresent(entry -> System.out.println("Student with highest average marks:"+ entry.getKey().name() +" with average marks:"+ Math.round(entry.getValue())));
    }
}
