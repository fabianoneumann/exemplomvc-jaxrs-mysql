package com.example.produtomvc.controller;

import com.example.produtomvc.dao.ProdutoDAO;
import com.example.produtomvc.model.Produto;
import com.google.gson.Gson;

import javax.ws.rs.*;
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

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response criaProduto(Produto produto){
        return null;
    }

    @PUT
    @Path("{idProduto}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editaProduto(Produto produto, @PathParam("idProduto") int idProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (produtoDAO.buscaProdutoPorId(idProduto) != null){
//                return Response.ok(new Gson().toJson(produtoDAO.editarProduto(produto))).build();
        }

        return null;
    }

    @GET
    @Path("{idProduto}")
    @Produces("application/json")
    public Response findProdutoById(@PathParam("idProduto") int idProduto) {
        Produto produto = new Produto();

        //Falta pegar o produto do banco a partir do idProduto
        return Response.ok(new Gson().toJson(produto)).build();
    }
}
