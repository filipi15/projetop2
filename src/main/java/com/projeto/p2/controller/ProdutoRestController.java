package com.projeto.p2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.p2.model.Produto;
import com.projeto.p2.model.ProdutoService;

@RestController
@RequestMapping("/rest")
public class ProdutoRestController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/lista-produtos")
    public List<Produto> listaProdutos() {
        return produtoService.listarProdutos();
    }
}
