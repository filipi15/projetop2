package com.projeto.p2.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.p2.model.User;
import com.projeto.p2.model.UserService;

@Controller
public class PostController {

    @Autowired
    private ApplicationContext context;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo() {
        return "catalogo";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("user", new User());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String postCadastro(@ModelAttribute User user, Model model) {
        UserService cs = context.getBean(UserService.class);
		cs.inserirUser(user);
        return "redirect:/listaUsuarios";
    }

    @GetMapping("/listaUsuarios")
	public String listarUsers(Model model){
		UserService cs = context.getBean(UserService.class);
		ArrayList<User> users = (ArrayList<User>) cs.listarUsers();
		model.addAttribute("users",users);
		return "listaUsuarios";
	}
}
