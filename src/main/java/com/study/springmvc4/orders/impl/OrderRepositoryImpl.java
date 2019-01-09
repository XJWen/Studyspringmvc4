package com.study.springmvc4.orders.impl;

import com.study.springmvc4.orders.Order;
import com.study.springmvc4.orders.db.OrderOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class OrderRepositoryImpl implements OrderOperations {
    /**
     * 注入MongoOperations来实现MongoTemplate
     * **/
    @Autowired
    private MongoOperations operations;

    /**
     * 创建查询
     * **/
    @Override
    public List<Order> findOrdersByType(String t) {
        String type  = t.equals("NET")?"WEB":t;
        Criteria where = Criteria.where("type").is(t);
        Query query = Query.query(where);

        return operations.find(query,Order.class);
    }
}
