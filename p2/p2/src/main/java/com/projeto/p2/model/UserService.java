package com.projeto.p2.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void inserirUser(User user){
        userDAO.inserirUser(user);
    }
}
