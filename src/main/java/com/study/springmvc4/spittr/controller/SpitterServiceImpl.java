package com.study.springmvc4.spittr.controller;

import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.dao.Spittle;
import com.study.springmvc4.spittr.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Rmi服务端配置
 * **/
@Controller
public class SpitterServiceImpl {
    @Autowired
    SpitterService spitterService;

    @Bean
    public RmiServiceExporter rmiServiceExporter(SpitterService service){
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(service);
        exporter.setServiceName("SpitterService");
        exporter.setServiceInterface(SpitterService.class);
        return exporter;
    }

    /**
     * 通过配置Url并命名，声明此服务在本地机器上，服务的接口由setServiceInterface属性来指定
     * **/
    @Bean
    public RmiProxyFactoryBean spitterService(){
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        rmiProxy.setServiceUrl("rmi://loaclhost/SpitterService");
        rmiProxy.setServiceInterface(SpitterService.class);
        return rmiProxy;
    }

    public List<Spittle> getSpittles(String usename){
        Spitter spitter = spitterService.getSpitter(usename);
        return spitterService.getSpittlesForSpitter(spitter);
    }
 }
