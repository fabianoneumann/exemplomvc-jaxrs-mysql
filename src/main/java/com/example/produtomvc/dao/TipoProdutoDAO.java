package com.example.produtomvc.dao;

import com.example.produtomvc.factory.ConnectionFactory;
import com.example.produtomvc.model.Produto;
import com.example.produtomvc.model.TipoProduto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoProdutoDAO {

    private Connection connection;

    public TipoProdutoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void criaTabelaTipoProduto() {
        String sql = "CREATE TABLE IF NOT EXISTS tiposproduto (" +
                "idTipoProduto INT PRIMARY KEY AUTO_INCREMENT," +
                "nome VARCHAR(50) NOT NULL" +
                ");";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.execute();
            stmt.close();

            System.out.println("Tabela Tipo de Produto criada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastraTipoProduto(TipoProduto tipoProduto) {
        String sql = "INSERT INTO tiposproduto" +
                " (nome) " +
                "VALUES (?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tipoProduto.getNome());

            stmt.execute();
            ResultSet resultSet = stmt.getGeneratedKeys();
            while (resultSet.next()){
                tipoProduto.setIdTipoProduto(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TipoProduto> listaTiposProduto() {
        String sql = "SELECT * FROM tiposproduto";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            List<TipoProduto> tiposProduto = new ArrayList<>();
            TipoProduto tipoProduto;

            while (resultSet.next()) {
                tipoProduto = new TipoProduto();
                tipoProduto.setIdTipoProduto(resultSet.getInt("idTipoProduto"));
                tipoProduto.setNome(resultSet.getString("nome"));

                tiposProduto.add(tipoProduto);
            }

            return tiposProduto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TipoProduto buscaTipoProdutoPorId(int idTipoProduto) {
        String sql = "SELECT * FROM tiposproduto WHERE idTipoProduto = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idTipoProduto);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                TipoProduto tipoProduto = new TipoProduto();
                tipoProduto.setIdTipoProduto(resultSet.getInt("idTipoProduto"));
                tipoProduto.setNome(resultSet.getString("nome"));

                return tipoProduto;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public TipoProduto editaTipoProduto(TipoProduto tipoProduto) {
        return null;
    }

    public void deletaTipoProduto(TipoProduto tipoProduto) {
        String sql = "DELETE FROM tiposproduto WHERE idTipoProduto = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tipoProduto.getIdTipoProduto());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
