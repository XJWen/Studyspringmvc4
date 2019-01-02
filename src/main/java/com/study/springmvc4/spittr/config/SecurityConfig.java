package com.study.springmvc4.spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * 支持SpringScurity @EnableWebSecurity Spring应用 EnableWebMvcSecurity SpringMVC
 * **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
    DataSource dataSource ;

    /**
     *配置基于内存的用户存储和基于数据库表进行认证
     * **/
    @Override
    protected void configure(AuthenticationManagerBuilder builder)throws Exception{
        //inMemoryAuthentication()启用内存用户存储
        builder
                .inMemoryAuthentication()
                .withUser("user").password("1234").roles("USER").and()
                .withUser("admin").password("1230").roles("USER","ADMIN");
        //自定义数据库表单中认证用户,通过密码转码器进行加密
        builder
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,true" +
                        "from security_users where username=?")
                .authoritiesByUsernameQuery("select username,'ROLE_USER' " +
                        "from security_authorities where username=?")
                .passwordEncoder(new Pbkdf2PasswordEncoder("53cr3t"));
        //配置嵌入式的LDAP服务器
        builder
                .ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid=0)")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .contextSource()
                    .root("dc=hahuma,dc=com")
                    .ldif("classpath:users.ldif");
    }

}
