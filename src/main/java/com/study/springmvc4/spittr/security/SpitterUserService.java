package com.study.springmvc4.spittr.security;

import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.data.SpitterRepository;
import com.study.springmvc4.spittr.data.UserDetailsService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;
import java.util.List;

public class SpitterUserService implements UserDetailsService {
    private final SpitterRepository repository;

    /**
     * 注入SpitterRepository
     * **/
    public SpitterUserService(SpitterRepository repository) {
        this.repository = repository;
    }

    /**
     * 给每一个Spitter一个用户和相应的权限
     * **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Spitter spitter = repository.findByUsername(username);
        if (spitter!=null){
            List<GrantedAuthority> authorities = new ArrayList<>();
            //创建权限列表,授予相关权限
            authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));

            return new User(spitter.getUsername(),spitter.getPassword(),authorities);
        }
       throw new UsernameNotFoundException(
               "User'"+username+"' not found"
       );
    }

}
