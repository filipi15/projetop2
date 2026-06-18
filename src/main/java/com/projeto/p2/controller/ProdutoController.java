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

@Controller
public class ProdutoController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/produto")
    public String formProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "formproduto";
    }

    @PostMapping("/produto")
    public String postProduto(@ModelAttribute Produto produto, Model model) {
        ProdutoService ps = context.getBean(ProdutoService.class);
        ps.inserirProduto(produto);
        return "redirect:/produto/listar";
    }

    @GetMapping("/produto/listar")
    public String listarProdutos(Model model){
        ProdutoService ps = context.getBean(ProdutoService.class);
        ArrayList<Produto> produtos = (ArrayList<Produto>) ps.listarProdutos();
        model.addAttribute("produtos",produtos);
        return "listarproduto";
    }

    @GetMapping("/produto/{id}")
    public String verProduto(@PathVariable String id, Model model){
        ProdutoService ps = context.getBean(ProdutoService.class);
        Produto produto = ps.mostrarProduto(id);
        model.addAttribute("produto",produto);
        return "paginaproduto";
    }

    @GetMapping("/produto/{id}/editar")
    public String formAtualizar(@PathVariable("id") String uuid, Model model) {
        ProdutoService ps = context.getBean(ProdutoService.class);
        Produto produtoId = ps.mostrarProduto(uuid);
        model.addAttribute("produto",produtoId);
        model.addAttribute("id",uuid);
        return "formupdproduto";
    }

    @PostMapping("/produto/{id}/editar")
    public String atualizarProduto(@PathVariable("id") String id,
                                   Model model,
                                   @ModelAttribute Produto prod) {
        ProdutoService ps = context.getBean(ProdutoService.class);
        ps.atualizarProduto(prod,id);
        return "redirect:/produto/listar";
    }

    @PostMapping("/produto/{id}/deletar")
    public String deletarProduto(@PathVariable("id") String id,
                                 Model model) {
        ProdutoService ps = context.getBean(ProdutoService.class);
        ps.deletarProduto(id);
        return "redirect:/produto/listar";
    }
}
