package com.projeto.p2.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UserDAO {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirUser(User user) {
        String sql = "INSERT INTO users(nome,email,senha)" +
                     " VALUES (?,?,?)";
        Object[] obj = new Object[3];
        obj[0] = user.getNome();
        obj[1] = user.getEmail();
        obj[2] = passwordEncoder.encode(user.getSenha());
        jdbc.update(sql, obj);
    }

    public void inserirPerfil(String uuid){
        String sql = "INSERT INTO perfil(userid,cargo)" +
                     " VALUES (?::uuid,?)";
        Object[] obj = new Object[2];
        obj[0] = uuid;
        obj[1] = "cliente";
        jdbc.update(sql, obj);
    }

    public String obterUUID(String email){
        String sql = "SELECT id FROM users where email=?";
        Map<String,Object> mp = jdbc.queryForMap(sql,email);
        UUID uuid = (UUID) mp.get("id");
        return uuid.toString();
    }

    public void atualizarUser(User novo, String uuid){
        String sql = "UPDATE users " +
            "SET nome = ?, email = ? WHERE id = ?::uuid";
        Object[] obj = new Object[3];
        obj[0] = novo.getNome();
        obj[1] = novo.getEmail();
        obj[2] = uuid;
        jdbc.update(sql,obj);
    }

    public void deletarUser(String uuid){
        jdbc.update("DELETE FROM perfil where userid = ?::uuid", uuid);
        jdbc.update("DELETE FROM users where id = ?::uuid", uuid);
    }

    public User mostrarUser(String uuid){
        String sql = "SELECT * FROM users where id=?::uuid";
        return User.converter(jdbc.queryForMap(sql,uuid));
    }

    public ArrayList<User> listarUsers(){
        String sql = "SELECT * FROM users";
        return User.converterTodos(jdbc.queryForList(sql));
    }
}
