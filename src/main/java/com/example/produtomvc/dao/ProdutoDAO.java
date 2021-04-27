package com.example.produtomvc.dao;

import com.example.produtomvc.factory.ConnectionFactory;
import com.example.produtomvc.model.Produto;
import com.example.produtomvc.model.TipoProduto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void criaTabelaProduto() {
        String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                "idProduto INT PRIMARY KEY AUTO_INCREMENT," +
                "nome VARCHAR(50) NOT NULL," +
                "preco DECIMAL(10,2)," +
                "idTipoProduto INT," +
                "CONSTRAINT fk_idTipoProduto FOREIGN KEY (idTipoProduto)" +
                "REFERENCES tiposproduto(idTipoProduto)" +
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
                " (nome, preco, idTipoProduto) " +
                "VALUES (?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getTipoProduto().getIdTipoProduto());

            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();

            while (resultSet.next()) {
                p.setIdProduto(resultSet.getInt(1));
            }
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

                TipoProdutoDAO tpDAO = new TipoProdutoDAO();
                TipoProduto tipoProduto = tpDAO.buscaTipoProdutoPorId(resultSet.getInt("idTipoProduto"));
                produto.setTipoProduto(tipoProduto);

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto buscaProdutoPorId(int idProduto){
        String sql = "SELECT * FROM produtos WHERE idProduto = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idProduto);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Produto produto = new Produto();
                produto.setIdProduto(resultSet.getInt("idProduto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setPreco(resultSet.getDouble("preco"));

                //resultSet.getInt("idTipoProduto")
                TipoProdutoDAO tpDAO = new TipoProdutoDAO();
                TipoProduto tipoProduto = tpDAO.buscaTipoProdutoPorId(resultSet.getInt("idTipoProduto"));
                produto.setTipoProduto(tipoProduto);

                return produto;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Produto editaProduto(Produto p) {
        return null;
    }

    public void deletaProduto(Produto p) {
        String sql = "DELETE FROM produtos WHERE idProduto = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, p.getIdProduto());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
