package com.sistemaavaliacao.cadastroproduto.main;

import com.sistemaavaliacao.cadastroproduto.view.TelaProduto;

public class CadastroProduto {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaProduto().setVisible(true);
        });
    }
}
