package com.study.springmvc4.orders.impl;

import com.study.springmvc4.orders.Product;
import com.study.springmvc4.orders.config.SpringDataRedisConfig;
import com.study.springmvc4.orders.db.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    SpringDataRedisConfig config ;
    @Autowired
    private  RedisTemplate redis = config.getTemplate();

    @Override
    public void updateProdecut(Product product) {
        redis.opsForValue().set(product.getSku(),product);
    }

    @Override
    public Product getProduct(String key) {
        Product product = (Product) redis.opsForValue().get("123456");
        return product;
    }
}
