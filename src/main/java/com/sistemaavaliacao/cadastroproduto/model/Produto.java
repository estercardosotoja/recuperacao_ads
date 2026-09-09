package com.sistemaavaliacao.cadastroproduto.model;

public class Produto {

    private String nome;
    private double precoCusto;
    private int quantidade;
    private String categoria;

    public Produto(String nome, double precoCusto, int quantidade, String categoria) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public double calcularPercentualLucro() {

        if (categoria.equals("Alimento")) {
            return 15;

        } else if (categoria.equals("Elétrico")) {
            return 25;

        } else if (categoria.equals("Automotivo")) {
            return 30;

        } else if (categoria.equals("Limpeza")) {
            return 20;

        } else {
            return 10;
        }
    }

    public double calcularPrecoVenda() {

        double percentual = calcularPercentualLucro();

        double lucro = precoCusto * percentual / 100;

        return precoCusto + lucro;
    }

    public double calcularValorTotal() {

        return calcularPrecoVenda() * quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getCategoria() {
        return categoria;
    }
}