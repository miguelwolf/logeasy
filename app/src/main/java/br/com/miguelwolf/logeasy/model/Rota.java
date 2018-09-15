package br.com.miguelwolf.logeasy.model;

public class Rota {

    private String codigo;
    private String foto;
    private String nome;
    private String destino;
    private String placa;
    private String coordenadas;

    public Rota() {
    }

    public Rota(String codigo, String foto, String nome, String destino, String placa, String coordenadas) {
        this.codigo = codigo;
        this.foto = foto;
        this.nome = nome;
        this.destino = destino;
        this.placa = placa;
        this.coordenadas = coordenadas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
