package com.javawiz.stream;

import com.javawiz.model.Department;
import com.javawiz.model.Employee;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ManyFindQueryOfEmployees {

    public static void main(String[] args) {
        // Average salary of all employees
        System.out.println("--- Average Salary of Employees ---");
        double avgsal = Employee.getEmployees().stream().collect(
            Collectors.averagingDouble(Employee::salary)
        );

        System.out.println("Average Salary of Employees: " + avgsal);
        // Average salary per department
        System.out.println("--- Average Salary of Employees per Department ---");
        Employee.getEmployees().stream().collect(
            Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary))
            ).forEach((d, a) ->
                System.out.println("Average Salary of Employees in Department: " + d + " is " + a));

        // Average salary per department alternative approach
        System.out.println("--- Alternative Approach for Average Salary of Employees per Department ---");
        Employee.getEmployees().stream().collect(
            Collectors.groupingBy(Employee::department, Collectors.summarizingDouble(Employee::salary))
            ).forEach((d, s) ->
                System.out.println("Average Salary of Employees in Department: " + d + " is " + s.getAverage()));

        // Average salary per department name
        System.out.println("--- Average Salary of Employees per Department Name ---");
        Employee.getEmployees().stream()
            .collect(Collectors.groupingBy(emp -> emp.department().deptName(),
                                           Collectors.averagingDouble(Employee::salary)))
            .forEach((d, a) ->
                System.out.println("Average Salary of Employees in Department Name: " + d + " is " + a));

        // Department with highest average salary
        System.out.println("--- Department with Highest Average Salary ---");
        Employee.getEmployees().stream()
            .collect(Collectors.groupingBy(Employee::department,
                                           Collectors.averagingDouble(Employee::salary)))
            .entrySet().stream()
            .max(Entry.comparingByValue())
            .ifPresent(entry -> System.out.println("Department with highest average salary: " +
                    entry.getKey() + " with average salary: " + entry.getValue()));

        // Simplest approach for Employee with highest salary per department
        System.out.println("--- Simplest approach for max salary per department ---");
        Employee.getEmployees().stream()
            .collect(Collectors.groupingBy(Employee::department,
                                           Collectors.maxBy(Comparator.comparing(Employee::salary))
                     )
            ).forEach((dept, empOpt) -> empOpt.ifPresent(
                emp -> System.out.println("Top employee in department:" + dept.deptName() + " is " + emp)
            ));

        System.out.println("--- Alternative approach for max salary per department ---");
        Employee.getEmployees().stream()
            .collect(
                Collectors.toMap(
                    Employee::department,
                    Function.identity(),
                    BinaryOperator.maxBy(Comparator.comparingDouble(Employee::salary))
                )
            )
            .forEach((dept, emp) ->
                         System.out.println("Top employee in department:" + dept.deptName() + " is " + emp));

        // Employee with highest salary per department
        System.out.println("--- Max salary per department ---");
        Employee.getEmployees().stream()
            .collect(
                Collectors.groupingBy(
                    Employee::department,
                    Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary)),
                        Optional::get
                    )
                ))
            .forEach((dept, emp) ->
                         System.out.println("Top employee in department:" + dept.deptName() + " is " + emp));

        // Employee with highest salary
        System.out.println("--- Employee with Highest Salary ---");
        Employee.getEmployees().stream()
            .max(Comparator.comparingDouble(Employee::salary))
            .ifPresent(emp -> System.out.println("Employee with highest salary: " +
                    emp.name() + " with salary: " + emp.salary()));

        // Employee with lowest salary
        System.out.println("--- Employee with Lowest Salary ---");
        Employee.getEmployees().stream()
            .min(Comparator.comparingDouble(Employee::salary))
            .ifPresent(emp -> System.out.println("Employee with lowest salary: " +
                    emp.name() + " with salary: " + emp.salary()));

        //// Employee salary using summary statistics
        System.out.println("--- Employee Salary using Summary Statistics ---");
        var stats = Employee.getEmployees().stream()
            .collect(Collectors.summarizingDouble(Employee::salary));
        System.out.println("Max Salary: " + stats.getMax());
        System.out.println("Min Salary: " + stats.getMin());
        System.out.println("Average Salary: " + stats.getAverage());
        System.out.println("Sum of Salaries: " + stats.getSum());

        // Employee with 2nd highest salary
        System.out.println("--- Employee with 2nd Highest ---");
        get2ndHighestSalaryEmployee(Employee.getEmployees())
            .ifPresent(emp -> System.out.println("Employee with 2nd highest salary: " +
                    emp.name() + " with salary: " + emp.salary()));

        find2ndHighestSalariedEmployeePerDept();

        employeeWith2ndHighestSalaryByDeptAvoidSorting();

        // Employee with 2nd lowest salary
        System.out.println("--- Employee with 2nd Lowest ---");
        Employee.getEmployees().stream()
            .sorted(Comparator.comparingDouble(Employee::salary))
            .skip(1)
            .findFirst()
            .ifPresent(emp -> System.out.println("Employee with 2nd lowest salary: " +
                    emp.name() + " with salary: " + emp.salary()));

        // Sort Employees by Multiple Fields (dept, salary desc)
        System.out.println("--- Sort Employees by Department name and then by Salary desc ---");
        Employee.getEmployees().stream()
            .sorted(
                Comparator.comparing(
                    Employee::department, Comparator.comparing(Department::deptName)
                    ).thenComparing(Comparator.comparingDouble(Employee::salary).reversed())
            )
            .collect(Collectors.toCollection(LinkedHashSet::new))
            .forEach(emp -> System.out.println("Sort Employee: " + emp.name() +
                ", By Department name: " + emp.department().deptName() +
                ",  and then by Salary: " + emp.salary()));

        //filter list of employees where salary > 50000,
        // convert name to uppercase,
        // arrange employees in descending order on name
        // and return a list with full employee object satisfying all of the above conditions.
        System.out.println("--- Filtered, Mapped, Sorted Employee Names ---");
        Employee.getEmployees().stream()
            .filter(emp -> emp.salary() > 40000)
            .map(ManyFindQueryOfEmployees::mapToEmployeeNameToUpperCase)
            .sorted(Comparator.comparing(Employee::name).reversed())
            .toList()
            .forEach(emp -> System.out.println("Filtered Employee Name: " + emp));
    }

    /**
     * Finds and prints the employee with the 2nd highest salary in each department
     * using a priority queue to avoid sorting the entire list.
     * This approach is more efficient for large datasets.
     * Time Complexity: O(N log K) where N is number of employees in a department and K is 2 here.
     */
    private static void employeeWith2ndHighestSalaryByDeptAvoidSorting() {
        System.out.println("--- Employee with 2nd Highest (Approach 2) ---");
        Employee.getEmployees().stream()
            .collect(Collectors.groupingBy(
                Employee::department,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .collect(Collectors.toCollection(() ->
                                                             new PriorityQueue<>(
                                                                 Comparator.comparingDouble(Employee::salary)
                                                             )
                        ))
                )
            ))
            .forEach(print2());
    }

    private static Employee mapToEmployeeNameToUpperCase(Employee emp) {
        return Employee.builder()
            .id(emp.id())
            .name(emp.name().toUpperCase())
            .salary(emp.salary())
            .department(emp.department())
            .build();
    }

    /**
     * Finds and prints the employee with the 2nd highest salary in each department.
     * This approach sorts the list of employees in each department to find the 2nd highest.
     * Time Complexity: O(N log N) due to sorting for each department.
     */
    private static void find2ndHighestSalariedEmployeePerDept() {
        System.out.println("--- Employee with 2nd Highest per Department ---");
        Employee.getEmployees().stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),//collects all elements in the current group (e.g. employees in a department) into a List.
                                employees -> employees.stream().sorted(Comparator.comparingDouble(Employee::salary).reversed()).skip(1).findFirst()
                        )//after collecting to list, process the list to find 2nd highest salary employee.
                ))
                .forEach(print());
    }

    private static Optional<Employee> get2ndHighestSalaryEmployee(List<Employee> list) {
        return list.stream()
            .sorted(Comparator.comparingDouble(Employee::salary).reversed())
            .skip(1)
            .findFirst();
    }

    private static BiConsumer<Department, Optional<Employee>> print() {
        return (dept, empOpt) ->
                empOpt.ifPresent(emp ->
                        System.out.println(
                                "Employee with 2nd highest salary in Department: "
                                        + dept.deptName()
                                        + " is " + emp.name()
                                        + " with salary: " + emp.salary()
                        )
                );
    }

    private static BiConsumer<Department, PriorityQueue<Employee>> print2() {
        return (dept, pq) -> {
            if (pq.size() >= 2) {
                pq.poll(); // remove highest
                Employee secondHighest = pq.poll();
                System.out.println(
                        "Employee with 2nd highest salary in Department: "
                                + dept.deptName()
                                + " is " + secondHighest.name()
                                + " with salary: " + secondHighest.salary()
                );
            }
        };
    }
}
