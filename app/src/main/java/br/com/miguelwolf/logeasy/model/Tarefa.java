package br.com.miguelwolf.logeasy.model;


public class Tarefa {

    private int codigo;
    private int grauVisibilidade;
    private boolean visualizado;
    private boolean ativo;
    private Pessoa requerente;
    private Pessoa requisitado;
    private String descricao;


    public Tarefa() {
    }


    public Tarefa(int codigo, int grauVisibilidade, boolean visualizado, boolean ativo, Pessoa requerente, Pessoa requisitado, String descricao) {
        this.codigo = codigo;
        this.grauVisibilidade = grauVisibilidade;
        this.visualizado = visualizado;
        this.ativo = ativo;
        this.requerente = requerente;
        this.requisitado = requisitado;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getGrauVisibilidade() {
        return grauVisibilidade;
    }

    public void setGrauVisibilidade(int grauVisibilidade) {
        this.grauVisibilidade = grauVisibilidade;
    }

    public boolean isVisualizado() {
        return visualizado;
    }

    public void setVisualizado(boolean visualizado) {
        this.visualizado = visualizado;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Pessoa getRequerente() {
        return requerente;
    }

    public void setRequerente(Pessoa requerente) {
        this.requerente = requerente;
    }

    public Pessoa getRequisitado() {
        return requisitado;
    }

    public void setRequisitado(Pessoa requisitado) {
        this.requisitado = requisitado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
