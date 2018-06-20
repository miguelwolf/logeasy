package br.com.miguelwolf.logeasy.model;

/**
 * Classe utilizada para modelar a lista do fragment de Pesquisa.
 *
 * @author Miguel Wolf
 * @since 05/04/2018 16:54
 */
public class Pesquisa {

    private int id;
    private int foto;
    private String nome;
    private String identificacao;

    /**
     * Classe utilizada para modelar a lista do fragment de Pesquisa.
     */
    public Pesquisa() {}


    /**
     * Classe utilizada para modelar a lista do fragment de Pesquisa.
     *
     * @param id, número de identificação do motorista, cliente ou lugar
     * @param foto, foto do motorista, cliente ou lugar.
     * @param nome, nome do motorista, cliente ou lugar.
     * @param identificacao atributo de destaque para diferenciar um motorista de outro, o mesmo vale para cliente e lugar.
     */
    public Pesquisa(int id, int foto, String nome, String identificacao) {
        this.id = id;
        this.foto = foto;
        this.nome = nome;
        this.identificacao = identificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
}
