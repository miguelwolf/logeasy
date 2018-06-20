package br.com.miguelwolf.logeasy.model;

public class Pessoa extends Endereco{

    private int codigo;
    private String nome;
    private String cpfCnpj;
    private String email;
    private boolean ativo;
    private int tipo;

    public Pessoa() {
    }

    public Pessoa(int codigo, String nome, String cpfCnpj, String email, boolean ativo, int tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.ativo = ativo;
        this.tipo = tipo;
    }

    public Pessoa(int codigo, String endereco, int numero, String bairro, String complemento, String logradouro, String cidade, String uf, String cep, int codigo1, String nome, String cpfCnpj, String email, boolean ativo, int tipo) {
        super(codigo, endereco, numero, bairro, complemento, logradouro, cidade, uf, cep);
        this.codigo = codigo1;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.ativo = ativo;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
