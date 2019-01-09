package com.study.springmvc4.orders.db;

import com.study.springmvc4.orders.Order;

import java.util.List;

public interface OrderOperations {

    List<Order> findOrdersByType(String t);
}
