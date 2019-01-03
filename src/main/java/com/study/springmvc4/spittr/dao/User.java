package com.study.springmvc4.spittr.dao;

import org.springframework.security.core.GrantedAuthority;

public class User {
    private String username;
    private String password;
    private GrantedAuthority authority;

    public User() {
    }

    public User(String username, String password, GrantedAuthority authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GrantedAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(GrantedAuthority authority) {
        this.authority = authority;
    }
}
