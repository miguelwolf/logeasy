package br.com.miguelwolf.logeasy.model;

public class Pessoa extends Endereco{

    private String codigo;
    private int foto;
    private String nome;
    private String cpfCnpj;
    private String email;
    private boolean ativo;
    private int tipo;
    private Pessoa empresa;
    private Carro carro;

    public Pessoa() {
    }

    public Pessoa(String codigo, int foto, String nome, Carro carro) {
        this.codigo = codigo;
        this.foto = foto;
        this.nome = nome;
        this.carro = carro;
    }

    public Pessoa(String codigo, String nome, String cpfCnpj, String email, boolean ativo, int tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.ativo = ativo;
        this.tipo = tipo;
    }

    public Pessoa(String codigo, String endereco, int numero, String bairro, String complemento, String logradouro, String cidade, String uf, String cep, String codigo1, String nome, String cpfCnpj, String email, boolean ativo, int tipo) {
        super(codigo, endereco, numero, bairro, complemento, logradouro, cidade, uf, cep);
        this.codigo = codigo1;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.ativo = ativo;
        this.tipo = tipo;
    }

    public Pessoa(String codigo, String endereco, int numero, String bairro, String complemento, String logradouro, String cidade, String uf, String cep, String codigo1, int foto, String nome, String cpfCnpj, String email, boolean ativo, int tipo, Pessoa empresa, Carro carro) {
        super(codigo, endereco, numero, bairro, complemento, logradouro, cidade, uf, cep);
        this.codigo = codigo1;
        this.foto = foto;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.ativo = ativo;
        this.tipo = tipo;
        this.empresa = empresa;
        this.carro = carro;
    }


    @Override
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Pessoa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Pessoa empresa) {
        this.empresa = empresa;
    }
}
