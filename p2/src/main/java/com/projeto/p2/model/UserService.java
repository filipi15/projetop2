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

    public ArrayList<User> listarUsers(){
        return userDAO.listarUsers();
    }
}
