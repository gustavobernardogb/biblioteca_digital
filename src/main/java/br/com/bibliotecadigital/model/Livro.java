package br.com.bibliotecadigital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String genero;

    private String localizacao;

    public Livro() {
    }

    public Livro(String nome, String genero, String localizacao) {
        this.nome = nome;
        this.genero = genero;
        this.localizacao = localizacao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}