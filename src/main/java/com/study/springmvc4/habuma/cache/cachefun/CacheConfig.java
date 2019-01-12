package com.study.springmvc4.habuma.cache.cachefun;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @EnableCaching 会创建一个切面（aspect）并触发Spring缓存注解的切点（pointcut）
 * **/
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * 声明缓存管理器
     * ConcurrentMapCacheManager() 它的缓存存储是基于内存的，对于开发、测试或基础的应用来说足够了
     * **/
    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager();
    }

    /**
     * Redis 缓存管理器创建
     * **/
    @Bean
    public CacheManager redisCacheManger(RedisTemplate template){
        return new RedisCacheManager(template);
    }

    /**
     * Redis 连接工厂
     * **/
    @Bean
    public JedisConnectionFactory connectionFactory(){
        JedisConnectionFactory connectionFactory =
                new JedisConnectionFactory();
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

    /**
     * Redis 模板
     * **/
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,String> redisTemplate =
                new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
