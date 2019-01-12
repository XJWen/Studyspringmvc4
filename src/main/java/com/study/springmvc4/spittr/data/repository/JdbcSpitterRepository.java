package com.study.springmvc4.spittr.data.repository;

import com.study.springmvc4.spittr.config.DataSourceConfig;
import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.data.SpitterRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * 将Spitter存入缓存中，key为spitter.id
     * #result 方法的返回值
     * **/
    @Override
    @CachePut(value = "spitterCache",key = "#result.id")
    public Spitter save(Spitter spitter) {
        return spitter;
    }

    /**
     * Redis 注解 缓存的作用只限于JDBCRepository
     * @Cacheable 首先会在缓存中以key值为spitterCache查找条目，如果找到匹配了，就不会对方法进行调用，没匹配到，方法会被调用并且放回值以username的值为key存放到缓存中，常用于查找
     * @CachePut    并不会去缓存中检查匹配的值，目标方法总是会被调用，并将返回值添加到缓存中，常用于修改
     * **/
    @Override
    @Cacheable(value = "spitterCache")
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

    @Override
    public int eliteSweep() {
        return 0;
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
