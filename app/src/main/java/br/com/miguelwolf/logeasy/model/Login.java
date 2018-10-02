package br.com.miguelwolf.logeasy.model;

public class Login extends Pessoa{

    private String codigo;
    private String usuario;
    private String senha;

    public Login(){
    }

    public Login(String codigo, String usuario, String senha) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Login(String codigo, String nome, String cpfCnpj, String email, boolean ativo, int tipo, String codigo1, String usuario, String senha) {
        super(codigo, nome, cpfCnpj, email, ativo, tipo);
        this.codigo = codigo1;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
