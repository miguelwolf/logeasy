package br.com.miguelwolf.logeasy.model;

public class Pessoa extends Endereco{

    private int codigo;
    private String nome;
    private String cpfCnpj;
    private String email;
    private boolean ativo;
    private int tipo;
    private Carro carro;

    public static final int ADMINISTRADOR = 2;
    public static final int CAMINHONEIRO = 1;
    public static final int COMUM = 0;

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

    public Pessoa(int codigo, String endereco, int numero, String bairro, String complemento, String logradouro, String cidade, String uf, String cep, int codigo1, String nome, String cpfCnpj, String email, boolean ativo, int tipo, Carro carro) {
        super(codigo, endereco, numero, bairro, complemento, logradouro, cidade, uf, cep);
        this.codigo = codigo1;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.ativo = ativo;
        this.tipo = tipo;
        this.carro = carro;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
}
