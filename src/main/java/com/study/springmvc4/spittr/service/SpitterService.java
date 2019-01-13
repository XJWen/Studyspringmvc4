package com.study.springmvc4.spittr.service;

import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.dao.Spittle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpitterService {

    List<Spittle> getRecentSpittles(int count);

    void saveSpitttle(Spittle spittle);

    void saveSpitter(Spitter spitter);

    Spitter getSpitter(Long id);

    void startFollowing(Spitter follower,Spitter followee);

    List<Spittle> getSpittlesForSpitter(Spitter spitter);

    List<Spittle> getSpittlesForSpitter(String username);

    Spitter getSpitter(String username);
    Spittle getSpittleById(long id);
    void deleteSpittle(long id);
    List<Spitter> getAllSpitters();
}
