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

    /**
     * Json enviado no body da requisição post no postman
     * {
     * 	"nome": "Porção"
     * }
     * */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response criaTipoProduto(TipoProduto tipoProduto) {
        TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
        tipoProdutoDAO.cadastraTipoProduto(tipoProduto);
        return Response.ok(new Gson().toJson(tipoProduto)).build();
    }

    @GET
    @Produces("application/json")
    public Response listaTipoProduto() {
        TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
        List<TipoProduto> tiposProduto = tipoProdutoDAO.listaTiposProduto();
        return Response.ok(new Gson().toJson(tiposProduto)).build();
    }

    @GET
    @Path("{idTipoProduto}")
    @Produces("application/json")
    public Response buscaTipoProdutoPorId(@PathParam("idTipoProduto") int idTipoProduto) {
        TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
        TipoProduto tipoProduto = tipoProdutoDAO.buscaTipoProdutoPorId(idTipoProduto);
        return Response.ok(new Gson().toJson(tipoProduto)).build();
    }

    //TODO: FALTA FAZER
    @PUT
    @Path("{idTipoProduto}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editaTipoProduto(@PathParam("idTipoProduto") int idTipoProduto, TipoProduto tipoProduto) {
        return Response.ok(new Gson().toJson("FALTA FAZER")).build();
    }

    @DELETE
    @Path("{idTipoProduto}")
    @Produces("application/json")
    public Response deletaTipoProduto(@PathParam("idTipoProduto") int idTipoProduto) {
        TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
        TipoProduto tipoProduto = tipoProdutoDAO.buscaTipoProdutoPorId(idTipoProduto);
        tipoProdutoDAO.deletaTipoProduto(tipoProduto);
        return Response.ok(new Gson().toJson("Tipo de Produto removido com sucesso!")).build();
    }
}
