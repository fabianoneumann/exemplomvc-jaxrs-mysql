package com.example.produtomvc.controller;

import com.example.produtomvc.dao.ProdutoDAO;
import com.example.produtomvc.model.Produto;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("produtos")
public class ProdutoController {
    @GET
    @Produces("application/json")
    public Response listaProdutos(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listaProdutos();

        return Response.ok(new Gson().toJson(produtos)).build();
    }
}
