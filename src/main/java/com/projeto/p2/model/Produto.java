package com.projeto.p2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Produto {

    private String id, artista, album, formato, genero, preco, ano, imagem;

    //Construtor para a pagina do formulario
    public Produto(){

    }

    //Construtor bom para Select
    public Produto(String id, String artista, String album, String formato,
                   String genero, String preco, String ano, String imagem) {
        this.id = id;
        this.artista = artista;
        this.album = album;
        this.formato = formato;
        this.genero = genero;
        this.preco = preco;
        this.ano = ano;
        this.imagem = imagem;
    }

    public String getId() {
        return id;
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public String getFormato() {
        return formato;
    }

    public String getGenero() {
        return genero;
    }

    public String getPreco() {
        return preco;
    }

    public String getAno() {
        return ano;
    }

    public String getImagem() {
        return imagem;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public static Produto converter(Map<String,Object> registro){
        UUID id = (UUID) registro.get("id");
        String artista = (String) registro.get("artista");
        String album = (String) registro.get("album");
        String formato = (String) registro.get("formato");
        String genero = (String) registro.get("genero");
        String preco = (String) registro.get("preco");
        String ano = (String) registro.get("ano");
        String imagem = (String) registro.get("imagem");
        return new Produto(id.toString(), artista, album, formato, genero, preco, ano, imagem);
    }

    public static ArrayList<Produto> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Produto> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}
