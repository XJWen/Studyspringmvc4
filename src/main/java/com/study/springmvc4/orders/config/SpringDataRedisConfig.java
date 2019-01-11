package com.study.springmvc4.orders.config;

import com.study.springmvc4.orders.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@EnableRedisRepositories
public class SpringDataRedisConfig {

    /**
     * Redis 连接工厂
     * 借助RedisConnection可以存储和读取数据
     * **/
    @Bean
    public RedisConnectionFactory factory(){
        return new JedisConnectionFactory();
    }

    /**
     * RedisTemplate 能够持久化各种类型key和value，不局限于字节数组
     * **/
    @Bean
    public RedisTemplate getTemplate() {
        RedisConnectionFactory factory = this.factory();
        RedisTemplate<String, Product> redis = new RedisTemplate<>();
        redis.setConnectionFactory(factory);
        return redis;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory){
        return new StringRedisTemplate(factory);
    }
    /**
     * 针对Product进行序列化操作所得模板
     * **/
    @Bean
    public RedisTemplate<String,Product> getProductTemplate() {
        RedisConnectionFactory factory = this.factory();
        RedisTemplate<String, Product> redis = new RedisTemplate<>();
        redis.setConnectionFactory(factory);
        redis.setValueSerializer(new Jackson2JsonRedisSerializer<Product>(Product.class));
        return redis;
    }
}
