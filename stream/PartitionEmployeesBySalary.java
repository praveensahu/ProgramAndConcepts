package com.javawiz.stream;

import com.javawiz.model.Employee;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionEmployeesBySalary {

    public static void main(String[] args) {
        //Partition Employees by Salary (>30k and <=30k)
        Map<Boolean, List<Employee>> partition = Employee.getEmployees()
            .stream()
            .collect(Collectors.partitioningBy(e -> e.salary() > 30000));

        System.out.println("Employees with salary > 50k:");
        partition.get(true).forEach(System.out::println);
    }
}
