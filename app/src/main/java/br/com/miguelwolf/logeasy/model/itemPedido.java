package br.com.miguelwolf.logeasy.model;

public class itemPedido {

    private int codigo;
    private int quantidade;
    private Produto produto;
    private Pedido pedido;

    public itemPedido() {
    }

    public itemPedido(int codigo, int quantidade, Produto produto, Pedido pedido) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.produto = produto;
        this.pedido = pedido;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
