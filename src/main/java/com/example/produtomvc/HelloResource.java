package com.example.produtomvc;

import javax.ws.rs.*;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String listaProdutos() {
        return "Hello, World!";
    }

    @GET
    @Path("pt-br")
    @Produces("text/plain")
    public String olaMundo() {
        return "Olá, Mundo!";
    }
}