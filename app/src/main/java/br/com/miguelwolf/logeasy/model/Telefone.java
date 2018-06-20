package br.com.miguelwolf.logeasy.model;

public class Telefone {

    private int codigo;
    private int ddd;
    private String telefone;
    private String tipo;
    private Pessoa pessoa;

    public Telefone() {
    }

    public Telefone(int codigo, int ddd, String telefone, String tipo) {
        this.codigo = codigo;
        this.ddd = ddd;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
