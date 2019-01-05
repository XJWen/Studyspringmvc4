package com.study.springmvc4.spittr.data.repository;

import com.study.springmvc4.spittr.config.DataSourceConfig;
import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.data.SpitterRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.sql.DataSource;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {

    private JdbcOperations operations;
    private static final String SQL_INSERT_SPITTER =
            "insert into spitter(username,password,fullname)values(?,?,?)";
    private static final String SQL_UPDATE_SPITTER=
            "update spitter set username=?,password=?fullname=?"+"where id=?";
    private static final String SQL_SELECT_SPITTER=
            "select id,username,fullname from spitter where id=?";

    {
        DataSourceConfig config = new DataSourceConfig();
        DataSource dataSource=config.boneCPDataSource();
        spitterRepository(config.jdbcTemplate(dataSource));
    }

    @Inject
    public JdbcSpitterRepository(JdbcOperations operations){
        this.operations = operations;
    }

    public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate){
        return new JdbcSpitterRepository(jdbcTemplate);
    }

    @Override
    public void save(Spitter spitter) {

    }

    @Override
    public Spitter findByUsername(String username) {
        return null;
    }

    @Override
    public void addSpitter(Spitter spitter) {
        operations.update(SQL_UPDATE_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullname(),
                spitter.getId()
                );
    }
}
