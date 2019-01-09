package com.study.springmvc4.orders.db;

import com.study.springmvc4.orders.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String>,OrderOperations {

    //?0 指代第0个参数，在这里指t
    @Query("{'customer':'Chuck Wagon','type':?0}")
    List<Order> findChucksOrders(String t);
}
