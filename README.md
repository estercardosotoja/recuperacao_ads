# Cadastro de Produtos

Aplicação desktop para cadastro e cálculo de produtos, desenvolvida em Java com interface gráfica Swing e gerenciamento de build pelo Maven.

## Regras de lucro

| Categoria | Percentual de lucro |
| --- | ---: |
| Alimento | 15% |
| Elétrico | 25% |
| Automotivo | 30% |
| Limpeza | 20% |
| Outros | 10% |

O preço de venda é calculado adicionando o percentual de lucro ao preço de custo. O valor total é obtido multiplicando o preço de venda pela quantidade.

## Como executar

Clone ou abra o projeto e, no diretório raiz, execute:

```bash
mvn clean compile
mvn exec:java
```

## Estrutura do projeto

```text
src/main/java/com/sistemaavaliacao/cadastroproduto/
├── main/CadastroProduto.java   # Ponto de entrada da aplicação
├── model/Produto.java           # Modelo e regras de cálculo
└── view/TelaProduto.java        # Interface gráfica Swing
```

## Tecnologias

- Java 17
- Swing
- Maven
