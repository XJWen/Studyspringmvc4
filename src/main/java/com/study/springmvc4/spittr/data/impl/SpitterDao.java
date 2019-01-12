package com.study.springmvc4.spittr.data.impl;

import com.study.springmvc4.spittr.config.DataSourceConfig;
import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.data.SpitterRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpitterDao implements SpitterRepository {
    private static final String SQL_INSERT_SPITTER =
            "insert into spitter(username,password,fullname)values(?,?,?)";
    private static final String SQL_UPDATE_SPITTER=
            "update spitter set username=?,password=?fullname=?"+"where id=?";
    private static final String SQL_SELECT_SPITTER=
            "select id,username,fullname from spitter where username=?";
    private DataSourceConfig dataConfig = new DataSourceConfig();
    private DataSource dataSource = dataConfig.embeddedDataSource();

    @Override
    public Spitter save(Spitter spitter) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_SPITTER);
            preparedStatement.setString(1,spitter.getUsername());
            preparedStatement.setString(2,spitter.getPassword());
            preparedStatement.setString(3,spitter.getFullname());
            preparedStatement.setString(4,spitter.getId().toString());
            preparedStatement.execute();
        }catch (SQLException ex){

        }finally {
            try {
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            }catch (SQLException ex){

            }
        }
        return null;
    }

    @Override
    public Spitter findByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_SPITTER);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            Spitter spitter = null;
            if (resultSet.next()){
                spitter = new Spitter();
                spitter.setId(resultSet.getLong("id"));
                spitter.setUsername(resultSet.getString("username"));
                spitter.setFullname(resultSet.getString("fullname"));
            }
            return  spitter;
        }catch (SQLException ex){

        }finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            }catch (SQLException ex){

            }
        }
        return  null;
    }

    /**
     * 以下是jdbc的冗余表现
     * **/
    @Override
    public void addSpitter(Spitter spitter) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_SPITTER);
            preparedStatement.setString(1,spitter.getUsername());
            preparedStatement.setString(2,spitter.getPassword());
            preparedStatement.setString(3,spitter.getFullname());
            preparedStatement.execute();
        }catch (SQLException ex){

        }finally {
            try {
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            }catch (SQLException ex){

            }
        }
    }

    @Override
    public int eliteSweep() {
        return 0;
    }
}
