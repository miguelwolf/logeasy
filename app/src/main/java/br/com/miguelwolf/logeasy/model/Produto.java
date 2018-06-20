package br.com.miguelwolf.logeasy.model;

public class Produto {

    private int codigo;
    private String descricao;
    private String descricaoAbreviada;
    private int codExterno;
    private int quantidade;
    private double preco;

    public Produto() {
    }

    public Produto(int codigo, String descricao, String descricaoAbreviada, int codExterno, int quantidade, double preco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.codExterno = codExterno;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public int getCodExterno() {
        return codExterno;
    }

    public void setCodExterno(int codExterno) {
        this.codExterno = codExterno;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
