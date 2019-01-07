package com.study.springmvc4.spittr.data.repository;

import com.study.springmvc4.spittr.config.DataSourceConfig;
import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.data.SpitterRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {

    private JdbcOperations operations;
    private static final String SQL_INSERT_SPITTER =
            "insert into spitter(username,password,fullname)values(?,?,?)";
    private static final String SQL_UPDATE_SPITTER=
            "update spitter set username=?,password=?fullname=?"+"where id=?";
    private static final String SQL_SELECT_SPITTER=
            "select id,username,fullname from spitter where username=?";

    {
        DataSourceConfig config = new DataSourceConfig();
        DataSource dataSource=config.boneCPDataSource();
        spitterRepository(config.jdbcTemplate(dataSource));
    }

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
    /*    return operations.queryForObject(SQL_SELECT_SPITTER,
                new SpitterRowMapper(),
                username);*/
          return operations.queryForObject(SQL_SELECT_SPITTER,
                  this::mapSpitter,username);
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

    private Spitter mapSpitter(ResultSet rs,int row)throws SQLException{
        return new Spitter(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("password")
        );
    }

    public static final class SpitterRowMapper implements RowMapper<Spitter>{

        @Override
        public Spitter mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Spitter(
                    resultSet.getLong("id"),
                    resultSet.getString("username"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("password")
            );
        }
    }
}
