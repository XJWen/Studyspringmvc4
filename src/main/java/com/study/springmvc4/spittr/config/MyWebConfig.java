package com.study.springmvc4.spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletRegistration;
import java.io.IOException;

/**
 * 支持SpingMVC配置 @EnableWebMvc
 * **/
@Configuration
@EnableWebMvc
@ComponentScan("com.study.springmvc4.spittr.web")
public class MyWebConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置jsp视图解析器
     * **/
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setPrefix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    /**
     * 配置静态资源的处理，将静态资源的请求转发到Servlet容器默认的Servlet中
     * **/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MultipartResolver multResolver()throws IOException{
        return new StandardServletMultipartResolver();
    }

}
