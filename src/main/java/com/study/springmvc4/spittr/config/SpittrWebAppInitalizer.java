package com.study.springmvc4.spittr.config;

import com.study.springmvc4.myapp.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.Properties;

/**
 * 扩展AbstractAnnotationConfigDispatcherServletInitializer
 * 会自动配置DispatcherServlet和Spring的应用上下文
 * **/
public class SpittrWebAppInitalizer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * ContextLoaderListener
     * 加载应用中的其他Bean，通常是驱动应用后端的中间层和数据层组件
     * **/
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{MyRootConfig.class};
    }
    /**
     * DispatcherServlet
     * 负责加载Web组件中的Bean，如控制器、视图解析以及处理器映射
     * */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MyWebConfig.class};
    }

    /**
     * 应用的默认Servlet
     * 映射"/"，处理应用的所有请求
     * 任何以".service"结束的URL请求都将由DispatcherServlet处理，它会把请求传递给匹配这个URL的控制器。
     * **/
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/","*.service"};
    }

    /**
     * 处理对"*.service"扩展的请求
     * **/
    @Bean
    public HandlerMapping httpinvokerMapping(){
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties properties = new Properties();
        properties.setProperty("/spitter.service","httpExportedSpitterService");
        mapping.setMappings(properties);
        return mapping;
    }

    @Override
    protected Filter[] getServletFilters(){
        return new Filter[]{new MyFilter()};
    }

    /**
     * 配置multipart解析器，通过DispatcherServlet来添加配置
     * MultipartConfigElement实例 参数为临时路径
     * **/
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        ServletRegistration.Dynamic servletRegistration =
                servletContext.addServlet("appServlet",dispatcherServlet);
        servletRegistration.addMapping("/");
        servletRegistration.setMultipartConfig(
                new MultipartConfigElement("/tmp/spittr/uploads")
        );
    }

    /**
     * MultipartConfigElement参数为：
     *上传文件的最大容量
     * 整个multipart请求的最大容量(以字节为单位)
     * 在上传过程中，如果文件大小达到了一个指定最大容量，将会写入临时路径中。默认值为0，也就是所有上传的文件都会写入磁盘上
     * **/
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        long maxFileSize = 1024*1024*2;
        long maxRequestSize = 1024*1024*4;
        registration.setMultipartConfig(
                new MultipartConfigElement("/tmp/spittr/uploads",
                        maxFileSize,maxRequestSize,0)
        );
    }
}
