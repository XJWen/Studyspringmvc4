package com.study.springmvc4.spittr.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Java校检API注解
 *
 * **/
public class Spitter {
    private Long id = 0L;
    @NotNull
    @Size(min = 5,max = 16)
    private String username;

    @NotNull
    @Size(min = 2,max = 30)
    private String firstname;
    @NotNull
    @Size(min = 2,max = 30)
    private String lastname;
    @NotNull
    @Size(min = 5,max = 25)
    private String password;

    public Spitter() {
    }

    public Spitter(Long id, String username, String firstname, String lastname, String password) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public Spitter(String username, String firstname, String lastname, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
