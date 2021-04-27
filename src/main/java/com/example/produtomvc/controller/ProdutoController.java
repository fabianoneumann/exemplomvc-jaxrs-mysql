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
    @Path("criatabela")
    @Produces("application/json")
    public Response criaTabelaProdutos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.criaTabelaProduto();
        return Response.ok(new Gson().toJson("Tabela produtos criada com sucesso!")).build();
    }

    /**
     * Json enviado no body da requisição post no postman
     * {
     * 	"nome": "Coca",
     * 	"preco": 2.99,
     * 	"tipoProduto": {
     * 		"idTipoProduto": 1
     *   }
     * }
     * */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response criaProduto(Produto produto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.cadastraProduto(produto);
        return Response.ok(new Gson().toJson(produto)).build();
    }

    @GET
    @Produces("application/json")
    public Response listaProdutos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listaProdutos();
        return Response.ok(new Gson().toJson(produtos)).build();
    }

    @GET
    @Path("{idProduto}")
    @Produces("application/json")
    public Response findProdutoById(@PathParam("idProduto") int idProduto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.buscaProdutoPorId(idProduto);
        return Response.ok(new Gson().toJson(produto)).build();
    }

    //TODO: FALTA AJUSTAR
    @PUT
    @Path("{idProduto}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editaProduto(Produto produto, @PathParam("idProduto") int idProduto) {
        return Response.ok(new Gson().toJson("FALTA FAZER")).build();
    }

    @DELETE
    @Path("{idProduto}")
    @Produces("application/json")
    public Response deletaProduto(@PathParam("idProduto") int idProduto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.buscaProdutoPorId(idProduto);
        produtoDAO.deletaProduto(produto);
        return Response.ok(new Gson().toJson("Produto removido com sucesso!")).build();
    }
}
