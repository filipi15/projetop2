package com.projeto.p2.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UserDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirUser(User user){
        String sql = "INSERT INTO users VALUES (?,?,?,?)";
        Object[] obj = new Object[4];

        obj[0] = user.getId();
        obj[1] = user.getNome();
        obj[2] = user.getEmail();
        obj[3] = user.getSenha();

        jdbc.update(sql, obj);
    }
}
