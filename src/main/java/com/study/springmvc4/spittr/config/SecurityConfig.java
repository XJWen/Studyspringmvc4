package com.study.springmvc4.spittr.config;

import com.study.springmvc4.spittr.data.SpitterRepository;
import com.study.springmvc4.spittr.security.SpitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

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

    @Autowired
    SpitterRepository repository;

    /**
     * 针对HTTP Request做认证
     *  .antMatchers Ant风格的通配符
     *  .regexMatchers 正则表达式
     *  .authenticated()允许认证过的用户访问
     *  .anyRequest().permitAll()无条件对任意请求允许访问，其余不需要认证和任何的权限
     *  .hasAuthority 具备某个权限
     *  .hasRole 具备某个权限，自带ROLE_前缀
     *  .and() 方法连接配置
     *  不能够在相同的路径下做不同的限制！！
     *  可以通过.access 通过SpEL来实现多层限制
     *   .requiresChannel() 视为需要安全通道
     *   .requiresInsecure() 始终通过HTTP请求
     *   .requiresSecure() 重定向到HTTPS
     *   .csrf() SpringSecurity处理CRSF（跨域请求伪造）的配置
     *   .csrf() SpringSecurity处理CRSF（跨域请求伪造）的配置
     *   .formLogin().loginPage("/login") 指定登录页面以及登录请求
     *   .httpBasic().realmName("Spitter") 启用HTTP Basic认证 realmName指定域
     *   HTTP Basic认证会直接通过HTTP请求本身，对要访问应用称许的用户进行认证，即跨程序访问
     * **/
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
//                .csrf()
//                    .disable()
                .formLogin()
                    .loginPage("/login")
                .and()
                .httpBasic()
//                    .realmName("Spitter")
                .and()
                .authorizeRequests()
//                .antMatchers("/spitters/me").hasAuthority("ROLE_SPITTER")
//                .antMatchers("/spitters/me").hasRole("SPITTER")
                    .antMatchers("/spitters/me").access("hasRole('SPITTER') and hasIpAddress('192.168.1.2')")
                    .antMatchers(HttpMethod.POST,"/spittles").authenticated()
                    .regexMatchers("/spitters/.*").authenticated()
                    .anyRequest().permitAll()
                .and()
                .requiresChannel()
                    .antMatchers("/spitter/form").requiresSecure()
                    .antMatchers("/").requiresInsecure();
    }

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

        //使用UserDetailsService来实现用户存储
        builder
                .userDetailsService(new SpitterUserService(repository));
    }

}
