package com.projeto.p2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private String id, nome, email, senha;

    public User(){

    };

    public User(String email, String id, String nome, String senha) {
        this.email = email;
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public User(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static User converter(Map<String,Object> registro){
        String nome = (String) registro.get("nome");
        String id = (String) registro.get("id");
        String email = (String) registro.get("email");
        return new User(id, nome, email);
    }

    public static ArrayList<User> converterTodos(List<Map<String,Object>> registros){
        ArrayList<User> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}
