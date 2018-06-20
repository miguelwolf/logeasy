package br.com.miguelwolf.logeasy.model;

public class Pedido {

    private int codigo;
    private double total;
    private String dataPedido;
    private double valorFrete;
    private boolean status;

    public Pedido() {
    }

    public Pedido(int codigo, double total, String dataPedido, double valorFrete, boolean status) {
        this.codigo = codigo;
        this.total = total;
        this.dataPedido = dataPedido;
        this.valorFrete = valorFrete;
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
