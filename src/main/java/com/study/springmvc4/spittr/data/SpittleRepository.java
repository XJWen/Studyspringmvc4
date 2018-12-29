package com.study.springmvc4.spittr.data;

import com.study.springmvc4.spittr.dao.Spittle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpittleRepository {

    List<Spittle> findSpittles(long max,long count);
    Spittle findOne(long id);
    void save(Spittle spittle);
}
