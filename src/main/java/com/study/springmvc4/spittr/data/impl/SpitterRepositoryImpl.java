package com.study.springmvc4.spittr.data.impl;

import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.data.SpitterSweeper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SpitterRepositoryImpl implements SpitterSweeper {

    @PersistenceContext
    private EntityManager manager;

    private Spitter spitter;

    @Override
    public int eliteSweep() {
        String update =
                "update Spitter spitter "
                +"set spitter.status='Elite'"
                        +"where spitter.status='Newbie'"
                        +"and spitter.id IN("+"select s from Spitter s where("
                        +"select count(spittles) from s.spittles spittles)>1000"
                        +")";
        return manager.createQuery(update).executeUpdate();
    }
}
