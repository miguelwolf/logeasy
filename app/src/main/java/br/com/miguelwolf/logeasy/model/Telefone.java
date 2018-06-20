package br.com.miguelwolf.logeasy.model;

public class Telefone extends Pessoa{

    private int codigo;
    private int ddd;
    private String telefone;
    private String tipo;

    public Telefone() {
    }

    public Telefone(int codigo, int ddd, String telefone, String tipo) {
        this.codigo = codigo;
        this.ddd = ddd;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public Telefone(int codigo, String nome, String cpfCnpj, String email, boolean ativo, int codigo1, int ddd, String telefone, String tipo) {
        super(codigo, nome, cpfCnpj, email, ativo);
        this.codigo = codigo1;
        this.ddd = ddd;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
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
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
