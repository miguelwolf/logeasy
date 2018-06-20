package br.com.miguelwolf.logeasy.model;

public class Historico {

    private int codigo;
    private int codSituacao;
    private String descricaoAbreviada;
    private String descricao;
    private String dataSituacao;
    private String ultimaAtualizacao;
    private String coordenadaAtual;
    private Pedido pedido;

    public Historico() {
    }

    public Historico(int codigo, int codSituacao, String descricaoAbreviada, String descricao, String dataSituacao, String ultimaAtualizacao, String coordenadaAtual, Pedido pedido) {
        this.codigo = codigo;
        this.codSituacao = codSituacao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.descricao = descricao;
        this.dataSituacao = dataSituacao;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.coordenadaAtual = coordenadaAtual;
        this.pedido = pedido;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodSituacao() {
        return codSituacao;
    }

    public void setCodSituacao(int codSituacao) {
        this.codSituacao = codSituacao;
    }

    public String getDescricaoAbreviada() {
        return descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataSituacao() {
        return dataSituacao;
    }

    public void setDataSituacao(String dataSituacao) {
        this.dataSituacao = dataSituacao;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public String getCoordenadaAtual() {
        return coordenadaAtual;
    }

    public void setCoordenadaAtual(String coordenadaAtual) {
        this.coordenadaAtual = coordenadaAtual;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
