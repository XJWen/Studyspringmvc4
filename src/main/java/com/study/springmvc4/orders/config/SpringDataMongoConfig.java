package com.study.springmvc4.orders.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 *  配置Mongo服务器运行在本地
 * **/
@Configuration
@EnableMongoRepositories(basePackages = "com.study.springmvc4.orders.db")
public class SpringDataMongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment environment;

    /**
     * 返回数据库名
     * **/
    @Override
    protected String getDatabaseName() {
        return "OrdersDB";
    }

    /**
     * 创建一个mongo客户端
     * **/
    @Override
    public Mongo mongo()throws UnknownHostException{
        //创建MongoDB认证
        MongoCredential credential =
                MongoCredential.createCredential(
                  environment.getProperty("mongo.username"),
                  "OrdersDB",
                  environment.getProperty("mongo.password").toCharArray()
                );
        return new MongoClient(new ServerAddress("loaclhost",27017)
                , Arrays.asList(credential));
    }
}
