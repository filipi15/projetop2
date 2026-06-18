package com.projeto.p2.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoDAO produtoDAO;

    public void inserirProduto(Produto produto){
        produtoDAO.inserirProduto(produto);
    }

    public Produto mostrarProduto(String uuid){
        return produtoDAO.mostrarProduto(uuid);
    }

    public ArrayList<Produto> listarProdutos(){
        return produtoDAO.listarProdutos();
    }

    public void atualizarProduto(Produto novo, String uuid){
        produtoDAO.atualizarProduto(novo, uuid);
    }

    public void deletarProduto(String uuid){
        produtoDAO.deletarProduto(uuid);
    }
}
