package com.study.springmvc4.spittr.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import com.jolbox.bonecp.BoneCPDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /**
     * java配置JNDI获取数据源
     * **/
    @Bean
    public JndiObjectFactoryBean jndiDataSource(){

        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiName("jdbc/SpittrDS");
        factoryBean.setResourceRef(true);
        factoryBean.setProxyInterface(javax.sql.DataSource.class);
        return factoryBean;
    }

    /**
     * BoneCP数据库连接池连接本地数据库
     * **/
    @Bean
    public BoneCPDataSource boneCPDataSource(){
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost/~/spitter");
        dataSource.setUsername("xjw");
        dataSource.setPassword("1232132123");
        //数据库连接池的最小连接数
        dataSource.setMinConnectionsPerPartition(5);
        //数据库连接池的最大连接数
        dataSource.setMaxConnectionsPerPartition(10);
        return dataSource;
    }

    /**
     * 基于jdbc驱动的数据源
     * **/
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource connectionSource = new DriverManagerDataSource();
        connectionSource.setDriverClassName("com.mysql.jdbc.Driver");
        connectionSource.setUrl("jdbc:mysql://localhost/~/spitter");
        connectionSource.setUsername("xjw");
        connectionSource.setPassword("1232132123");
        return  connectionSource;
    }
}
