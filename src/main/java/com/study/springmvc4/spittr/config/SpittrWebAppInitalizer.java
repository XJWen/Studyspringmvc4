package com.study.springmvc4.spittr.config;

import com.study.springmvc4.myapp.MyFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

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
     * **/
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters(){
        return new Filter[]{new MyFilter()};
    }

    /**
     * 配置multipart解析器，通过DispatcherServlet来添加配置
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

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        registration.setMultipartConfig(
                new MultipartConfigElement("/tmp/spittr/uploads")
        );
    }
}
