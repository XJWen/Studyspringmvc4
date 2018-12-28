package com.study.springmvc4.myapp.config;

import com.study.springmvc4.myapp.MyFilter;
import com.study.springmvc4.myapp.MyServlet;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * 向Web容器中注册其他组件
 * **/
public class MyServletInitializer implements WebApplicationInitializer {
    /**
     * 注册Servlet并映射到一个路径上
     * 注册Filter并添加Filter映射路径
     * **/
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);

        myServlet.addMapping("/custom/**");

        FilterRegistration.Dynamic filter = servletContext.addFilter("myFilter", MyFilter.class);

        filter.addMappingForUrlPatterns(null,false,"/custom/*");
    }
}
