package com.study.highig_springmvc4;

import com.study.highig_springmvc4.messageconverter.MyMessageConverter;
import com.study.highig_springmvc4.web.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("com.study.highig_springmvc4")
public class MySpringMVCConfig extends WebMvcConfigurerAdapter {//SpringMVC总配置类

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000);
        return multipartResolver;
    }

    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(demoInterceptor());
    }

    //通过url映射页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/uploaddemo");
        registry.addViewController("/converter").setViewName("/converter");

    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(converter());
    }
}
