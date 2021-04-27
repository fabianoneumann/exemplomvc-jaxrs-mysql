package com.example.produtomvc.controller;

import com.example.produtomvc.dao.ProdutoDAO;
import com.example.produtomvc.dao.TipoProdutoDAO;
import com.example.produtomvc.model.Produto;
import com.example.produtomvc.model.TipoProduto;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("tiposproduto")
public class TipoProdutoController {

    @GET
    @Path("criatabela")
    @Produces("application/json")
    public Response criaTabelaTipoProduto() {
        TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
        tipoProdutoDAO.criaTabelaTipoProduto();
        return Response.ok(new Gson().toJson("Tabela tiposproduto criada com sucesso!")).build();
    }

    @GET
    @Produces("application/json")
    public Response listaTipoProduto() {
        TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
        List<TipoProduto> tiposProduto = tipoProdutoDAO.listaTiposProduto();
        return Response.ok(new Gson().toJson(tiposProduto)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response criaTipoProduto(TipoProduto tipoProduto) {
        TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
        tipoProdutoDAO.cadastraTipoProduto(tipoProduto);
        return Response.ok(new Gson().toJson("Tipo de Produto cadastrado com sucesso!")).build();
    }

    //TODO: FALTA AJUSTAR
    @GET
    @Path("{idTipoProduto}")
    @Produces("application/json")
    public Response buscaTipoProdutoPorId(@PathParam("idTipoProduto") int idTipoProduto) {
        return Response.ok(new Gson().toJson("Tipo de Produto com o id informado, caso exista.")).build();
    }
}
