package com.study.springmvc4.spittr.exporter;

import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.dao.Spittle;
import com.study.springmvc4.spittr.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.util.List;

public class MyServiceExporter {

    @Autowired
    SpitterService spitterService;

    /**
     * 发布服务，将SpitterService发布为Rmi服务
     * 将bean导出Rmi服务
     * **/
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

    /**
     * HessianServiceExporter是一个Spring的控制器，它接受Hession请求，并将这些请求转换成被导出POJO的方法调用
     * 将bean导出为HessianService
     * **/
    @Bean
    public HessianServiceExporter hassianServiceExporter(SpitterService service){
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(SpitterService.class);
        return exporter;
    }

    /**
     * 导出成基于Http invoker的服务配置，服务端配置
     * **/
    @Bean
    public HttpInvokerServiceExporter httpInvokerServiceExporter(SpitterService service){
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(SpitterService.class);
        return exporter;
    }

    /**
     * 客户端配置代理
     * 标识远程服务端口位置
     * 当前Spitter实现得服务类接口
     * **/
    public HttpInvokerProxyFactoryBean spitterfactorybean(){
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/Spitter/spitter.service");
        proxy.setServiceInterface(SpitterService.class);
        return proxy;
    }

    public List<Spittle> getSpittles(String usename){
        Spitter spitter = spitterService.getSpitter(usename);
        return spitterService.getSpittlesForSpitter(spitter);
    }
}
