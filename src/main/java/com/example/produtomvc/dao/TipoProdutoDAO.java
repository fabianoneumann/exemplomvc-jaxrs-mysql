package com.example.produtomvc.dao;

import com.example.produtomvc.factory.ConnectionFactory;
import com.example.produtomvc.model.Produto;
import com.example.produtomvc.model.TipoProduto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    }

    public List<TipoProduto> listaTiposProduto() {
        return null;
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

    }
}
