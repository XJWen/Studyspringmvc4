package com.study.springmvc4.spittr.data;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *自定义UserDetailsService接口通过继承SpringSecurity的UserDetailsService来实现
 * **/
public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username)throws UsernameNotFoundException;
}
