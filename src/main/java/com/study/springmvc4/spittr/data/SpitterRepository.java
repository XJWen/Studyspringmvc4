package com.study.springmvc4.spittr.data;

import com.study.springmvc4.spittr.dao.Spitter;
import org.springframework.stereotype.Component;

@Component
public interface SpitterRepository {

    void save(Spitter spitter);

    Spitter findByUsername(String username);

    void addSpitter(Spitter spitter);
}
