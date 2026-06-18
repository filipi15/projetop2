package com.projeto.p2.model;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ProdutoDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirProduto(Produto produto) {
        String sql = "INSERT INTO produto(artista,album,formato,genero,preco,ano,imagem)" +
                     " VALUES (?,?,?,?,?,?,?)";
        Object[] obj = new Object[7];
        obj[0] = produto.getArtista();
        obj[1] = produto.getAlbum();
        obj[2] = produto.getFormato();
        obj[3] = produto.getGenero();
        obj[4] = produto.getPreco();
        obj[5] = produto.getAno();
        obj[6] = produto.getImagem();
        jdbc.update(sql, obj);
    }

    public void atualizarProduto(Produto novo, String uuid){
        String sql = "UPDATE produto " +
            "SET artista = ?, album = ?, formato = ?, genero = ?, preco = ?, ano = ?, imagem = ? " +
            "WHERE id = ?::uuid";
        Object[] obj = new Object[8];
        obj[0] = novo.getArtista();
        obj[1] = novo.getAlbum();
        obj[2] = novo.getFormato();
        obj[3] = novo.getGenero();
        obj[4] = novo.getPreco();
        obj[5] = novo.getAno();
        obj[6] = novo.getImagem();
        obj[7] = uuid;
        jdbc.update(sql,obj);
    }

    public void deletarProduto(String uuid){
        String sql = "DELETE FROM produto where id = ?::uuid";
        jdbc.update(sql,uuid);
    }

    public Produto mostrarProduto(String uuid){
        String sql = "SELECT * FROM produto where id=?::uuid";
        return Produto.converter(jdbc.queryForMap(sql,uuid));
    }

    public ArrayList<Produto> listarProdutos(){
        String sql = "SELECT * FROM produto";
        return Produto.converterTodos(jdbc.queryForList(sql));
    }
}
