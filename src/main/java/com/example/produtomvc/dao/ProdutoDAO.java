package com.example.produtomvc.dao;

import com.example.produtomvc.factory.ConnectionFactory;
import com.example.produtomvc.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    Connection connection;

    public ProdutoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void criaTabelaProduto() {
        String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                "idProduto INT PRIMARY KEY AUTO_INCREMENT," +
                "nome VARCHAR(50) NOT NULL," +
                "preco DECIMAL(10,2)" +
                ");";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.execute();
            stmt.close();

            System.out.println("Tabela Produto criada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastraProduto(Produto p) {
        String sql = "INSERT INTO produtos" +
                " (idProduto, nome, preco) " +
                "VALUES (?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, p.getIdProduto());
            stmt.setString(2, p.getNome());
            stmt.setDouble(3, p.getPreco());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listaProdutos() {
        String sql = "SELECT * FROM produtos";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            List<Produto> produtos = new ArrayList<>();
            Produto produto;

            while (resultSet.next()) {
                produto = new Produto();
                produto.setIdProduto(resultSet.getInt("idProduto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setPreco(resultSet.getDouble("preco"));

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto buscaProdutoPorId(int idProduto){
        return null;
    }

    public void editarProduto(Produto p) {

    }

    public void deletarProduto(Produto p) {

    }
}
