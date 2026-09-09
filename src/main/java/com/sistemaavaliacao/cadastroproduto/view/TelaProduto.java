package com.sistemaavaliacao.cadastroproduto.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.sistemaavaliacao.cadastroproduto.model.Produto;

public class TelaProduto extends JFrame {

    private JTextField txtNome;
    private JComboBox<String> cbCategoria;
    private JTextField txtPrecoCusto;
    private JTextField txtQuantidade;
    private JTextField txtPercentual;

    private JLabel lblPrecoVenda;
    private JLabel lblValorTotal;

    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    public TelaProduto() {
        setTitle("Cadastro de Produtos");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        criarTela();
    }

    private void criarTela() {
        setTitle("Cadastro de Produtos");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel("Cadastro de Produtos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new GridLayout(7, 2, 10, 8));
        painelCampos.setBorder(BorderFactory.createTitledBorder("Dados do Produto"));

        painelCampos.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelCampos.add(txtNome);

        painelCampos.add(new JLabel("Categoria:"));
        cbCategoria = new JComboBox<>();
        cbCategoria.addItem("Alimento");
        cbCategoria.addItem("Elétrico");
        cbCategoria.addItem("Automotivo");
        cbCategoria.addItem("Limpeza");
        cbCategoria.addItem("Outros");
        painelCampos.add(cbCategoria);

        painelCampos.add(new JLabel("Preço de custo:"));
        txtPrecoCusto = new JTextField();
        painelCampos.add(txtPrecoCusto);

        painelCampos.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        painelCampos.add(txtQuantidade);

        painelCampos.add(new JLabel("Percentual de lucro:"));
        txtPercentual = new JTextField();
        txtPercentual.setEditable(false);
        painelCampos.add(txtPercentual);

        painelCampos.add(new JLabel("Preço de venda:"));
        lblPrecoVenda = new JLabel("R$ 0,00");
        painelCampos.add(lblPrecoVenda);

        painelCampos.add(new JLabel("Valor total:"));
        lblValorTotal = new JLabel("R$ 0,00");
        painelCampos.add(lblValorTotal);

        JPanel painelBotoes = new JPanel();
        JButton btnCalcular = new JButton("CALCULAR");
        JButton btnAdicionar = new JButton("ADICIONAR");
        JButton btnLimpar = new JButton("LIMPAR");

        painelBotoes.add(btnCalcular);
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnLimpar);

        JPanel painelFormulario = new JPanel(new BorderLayout(10, 10));
        painelFormulario.add(painelCampos, BorderLayout.CENTER);
        painelFormulario.add(painelBotoes, BorderLayout.SOUTH);
        painelPrincipal.add(painelFormulario, BorderLayout.CENTER);

        modeloTabela = new DefaultTableModel();

        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Categoria");
        modeloTabela.addColumn("Custo");
        modeloTabela.addColumn("Lucro");
        modeloTabela.addColumn("Preço Venda");
        modeloTabela.addColumn("Quantidade");
        modeloTabela.addColumn("Valor Total");

        tabelaProdutos = new JTable(modeloTabela);
        tabelaProdutos.setRowHeight(25);

        JScrollPane scrollTabela = new JScrollPane(tabelaProdutos);
        scrollTabela.setBorder(BorderFactory.createTitledBorder("Produtos cadastrados"));

        scrollTabela.setPreferredSize(new Dimension(850, 250));
        painelPrincipal.add(scrollTabela, BorderLayout.SOUTH);
        cbCategoria.addActionListener(e -> {
            atualizarPercentual();
        });

        btnCalcular.addActionListener(e -> {
            calcular();
        });

        btnAdicionar.addActionListener(e -> {
            adicionarProduto();
        });

        btnLimpar.addActionListener(e -> {
            limparCampos();
        });

        setContentPane(painelPrincipal);
        atualizarPercentual();
    }

    private void atualizarPercentual() {
        String categoria = cbCategoria.getSelectedItem().toString();
        double percentual;

        switch (categoria) {
            case "Alimento":
                percentual = 15;
                break;
            case "Elétrico":
                percentual = 25;
                break;
            case "Automotivo":
                percentual = 30;
                break;
            case "Limpeza":
                percentual = 20;
                break;
            default:
                percentual = 10;
                break;
        }
        txtPercentual.setText(percentual + "%");
    }

    private Produto criarProduto() {
        String nome = txtNome.getText();
        double precoCusto = Double.parseDouble(txtPrecoCusto.getText().replace(",", "."));

        int quantidade = Integer.parseInt(txtQuantidade.getText());
        String categoria = cbCategoria.getSelectedItem().toString();

        if (nome.isEmpty()) {
            throw new IllegalArgumentException(
                "Informe o nome do produto."
            );
        }

        if (precoCusto <= 0) {
            throw new IllegalArgumentException(
                "O preço de custo deve ser maior que zero."
            );
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                "A quantidade deve ser maior que zero."
            );
        }
        return new Produto( nome, precoCusto, quantidade, categoria );
    }

    private void calcular() {
        try {
            Produto produto = criarProduto();
            double precoVenda = produto.calcularPrecoVenda();
            double valorTotal = produto.calcularValorTotal();

            lblPrecoVenda.setText( String.format( "R$ %.2f", precoVenda ) );
            lblValorTotal.setText( String.format( "R$ %.2f", valorTotal ) );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog( this, "Informe valores numéricos válidos." );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog( this, e.getMessage() );
        }
    }

    private void adicionarProduto() {
        try {
            Produto produto = criarProduto();
            modeloTabela.addRow(
                    new Object[]{
                        produto.getNome(),
                        produto.getCategoria(),
                        String.format( "R$ %.2f", produto.getPrecoCusto() ),
                        String.format( "%.0f%%", produto.calcularPercentualLucro() ),
                        String.format( "R$ %.2f", produto.calcularPrecoVenda() ),
                        produto.getQuantidade(),
                        String.format( "R$ %.2f", produto.calcularValorTotal() )
                    }
            );
            lblPrecoVenda.setText(String.format("R$ %.2f", produto.calcularPrecoVenda()));
            lblValorTotal.setText(String.format("R$ %.2f", produto.calcularValorTotal()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog( this, "Informe valores numéricos válidos." );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog( this, e.getMessage() );
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtPrecoCusto.setText("");
        txtQuantidade.setText("");
        cbCategoria.setSelectedIndex(0);
        lblPrecoVenda.setText("R$ 0,00");
        lblValorTotal.setText("R$ 0,00");
        txtNome.requestFocus();
    }
}
