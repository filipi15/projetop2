package com.projeto.p2.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void inserirUser(User user){
        userDAO.inserirUser(user);
    }

    public User mostrarUser(String uuid){
        return userDAO.mostrarUser(uuid);
    }

    public ArrayList<User> listarUsers(){
        return userDAO.listarUsers();
    }

    public void atualizarUser(User novo, String uuid){
        userDAO.atualizarUser(novo, uuid);
    }

    public void deletarUser(String uuid){
        userDAO.deletarUser(uuid);
    }

    public void inserirPerfil(String uuid){
        userDAO.inserirPerfil(uuid);
    }

    public String obterUUID(String email){
        return userDAO.obterUUID(email);
    }
}
