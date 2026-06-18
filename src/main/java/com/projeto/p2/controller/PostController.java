package com.projeto.p2.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.p2.model.Produto;
import com.projeto.p2.model.ProdutoService;
import com.projeto.p2.model.User;
import com.projeto.p2.model.UserService;

@Controller
public class PostController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String index(Model model) {
        ProdutoService ps = context.getBean(ProdutoService.class);
        ArrayList<Produto> produtos = (ArrayList<Produto>) ps.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        ProdutoService ps = context.getBean(ProdutoService.class);
        ArrayList<Produto> produtos = (ArrayList<Produto>) ps.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "catalogo";
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
        String uuid = cs.obterUUID(user.getEmail());
        cs.inserirPerfil(uuid);
        return "redirect:/login";
    }

    @GetMapping("/listaUsuarios")
    public String listarUsers(Model model){
        UserService cs = context.getBean(UserService.class);
        ArrayList<User> users = (ArrayList<User>) cs.listarUsers();
        model.addAttribute("users",users);
        return "listaUsuarios";
    }

    @PostMapping("/usuario/{id}/deletar")
    public String deletarUsuario(@PathVariable("id") String id) {
        UserService cs = context.getBean(UserService.class);
        cs.deletarUser(id);
        return "redirect:/listaUsuarios";
    }
}