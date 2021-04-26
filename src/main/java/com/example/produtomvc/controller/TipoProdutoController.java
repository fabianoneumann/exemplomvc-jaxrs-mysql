package com.example.produtomvc.controller;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("tiposprodutos")
public class TipoProdutoController {

    @GET
    @Produces("application/json")
    public Response listaTipoProdutos() {
        return Response.ok(new Gson().toJson("Lista de Tipos de Produtos")).build();
    }

    @GET
    @Path("{idTipoProduto}")
    @Produces("application/json")
    public Response buscaTipoProdutoPorId(@PathParam("idTipoProduto") int idTipoProduto) {
        return Response.ok(new Gson().toJson("Tipo de Produto com o id informado, caso exista.")).build();
    }
}
