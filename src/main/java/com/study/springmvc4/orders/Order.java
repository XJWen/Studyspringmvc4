package com.study.springmvc4.orders;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;
import java.util.LinkedHashSet;

@Document
@Data
public class Order {

    @Id
    private String id;
    @Field("client")//覆盖默认的域名，即mongo中存储的key为client
    private String customer;

    private String type;

    private Collection<Item> items = new LinkedHashSet<>();

    @Data
    private class Item {

        private Long id;

        private Order order;

        private String product;

        private double price;

        private int quantity;
    }
}
