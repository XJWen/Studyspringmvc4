package com.study.springmvc4.orders.db;

import com.study.springmvc4.orders.Product;

public interface ProductRepository {

    void updateProdecut(Product product);
    Product getProduct(String key);
}
