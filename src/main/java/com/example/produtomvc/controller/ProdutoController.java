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

    @GET
    @Produces("application/json")
    public Response listaProdutos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listaProdutos();
        return Response.ok(new Gson().toJson(produtos)).build();
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
        return Response.ok(new Gson().toJson("Produto cadastrado com sucesso")).build();
    }


    //TODO: FALTA AJUSTAR
    @PUT
    @Path("{idProduto}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editaProduto(Produto produto, @PathParam("idProduto") int idProduto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (produtoDAO.buscaProdutoPorId(idProduto) != null){
//                return Response.ok(new Gson().toJson(produtoDAO.editarProduto(produto))).build();
        }

        return null;
    }

    //TODO: FALTA AJUSTAR
    @GET
    @Path("{idProduto}")
    @Produces("application/json")
    public Response findProdutoById(@PathParam("idProduto") int idProduto) {
        Produto produto = new Produto();

        //Falta pegar o produto do banco a partir do idProduto
        return Response.ok(new Gson().toJson(produto)).build();
    }
}
