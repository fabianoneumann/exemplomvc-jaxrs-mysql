package com.example.produtomvc.model;

public class TipoProduto {
    private int idTipoProduto;
    private String nome;

    public TipoProduto() {
    }

    public TipoProduto(String nome) {
        this.nome = nome;
    }

    public int getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(int idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TipoProduto{" +
                "idTipoProduto=" + idTipoProduto +
                ", nome='" + nome + '\'' +
                '}';
    }
}
