package com.javawiz.stream;

import com.javawiz.model.Order;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TopCustomerPerOrderStatus {

    public static void main(String[] args) {
        Order.getOrders()
            .stream()
            .collect(Collectors.groupingBy(
                Order::status,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingDouble(Order::amount)),
                    orderOpt -> orderOpt.map(Order::customerId).orElse(null)
            )))
            .forEach((status, customer) ->
                System.out.println("Top customer for status:" + status + " with customerId:" + customer));
    }
}
