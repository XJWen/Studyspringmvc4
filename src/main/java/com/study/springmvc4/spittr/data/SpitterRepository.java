package com.study.springmvc4.spittr.data;

import com.study.springmvc4.spittr.dao.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SpitterRepository extends  SpitterSweeper{

    void save(Spitter spitter);

    Spitter findByUsername(String username);

    void addSpitter(Spitter spitter);
}
