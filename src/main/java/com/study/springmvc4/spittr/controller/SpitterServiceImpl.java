package com.study.springmvc4.spittr.controller;


import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.dao.Spittle;
import com.study.springmvc4.spittr.service.SpitterService;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * Rmi服务端配置
 * **/
@Controller
public class SpitterServiceImpl implements SpitterService {

    @Override
    public List<Spittle> getRecentSpittles(int count) {
        return null;
    }

    @Override
    public void saveSpitttle(Spittle spittle) {

    }

    @Override
    public void saveSpitter(Spitter spitter) {

    }

    @Override
    public Spitter getSpitter(Long id) {
        return null;
    }

    @Override
    public void startFollowing(Spitter follower, Spitter followee) {

    }

    @Override
    public List<Spittle> getSpittlesForSpitter(Spitter spitter) {
        return null;
    }

    @Override
    public List<Spittle> getSpittlesForSpitter(String username) {
        return null;
    }

    @Override
    public Spitter getSpitter(String username) {
        return null;
    }

    @Override
    public Spittle getSpittleById(long id) {
        return null;
    }

    @Override
    public void deleteSpittle(long id) {

    }

    @Override
    public List<Spitter> getAllSpitters() {
        return null;
    }
}
