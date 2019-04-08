package br.com.restful.api.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

import br.com.restful.api.controller.UtilitarioController;
import br.com.restful.api.model.Utilitario;

/**
 * Classe responsavel por conter os metodos REST de acesso ao webservice
 */
@Path("/utilitario")
public class UtilitarioResource {
	Gson gson = new Gson();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listarTodos() {
		String r = gson.toJson(new UtilitarioController().listarTodos());
		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		Utilitario utilitario = new UtilitarioController().buscarPorId(id);
		if (utilitario != null) {
			return Response.status(Response.Status.OK).entity(gson.toJson(utilitario)).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"false\"}").build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarUtilitarioJson(String u) {
		Utilitario utilitario = gson.fromJson(u, Utilitario.class);
		Utilitario newUtilitario = new UtilitarioController().gravarUtilitario(utilitario);
		if (!newUtilitario.getId().equals(null)) {
			return Response.status(Response.Status.OK).entity(gson.toJson(newUtilitario)).build();
		} else { 
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"false\"}").build();
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response altualizarUtilitario(String u) {
		Utilitario utilitario = gson.fromJson(u, Utilitario.class);
		boolean isUtilitarioAtualizado = new UtilitarioController().atualizarUtilitario(utilitario);
		if (isUtilitarioAtualizado == true) {
			return Response.status(Response.Status.OK).entity(u).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"false\"}").build();
		}

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarUtilitario(String u) {
		Utilitario utilitario = gson.fromJson(u, Utilitario.class);
		boolean isUtilitarioDeletado = new UtilitarioController().deletarUtilitario(utilitario);
		if (isUtilitarioDeletado == true) {
			return Response.status(Response.Status.OK).entity("{\"message\":\"true\"}").build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"false\"}").build();
		}

	}

}
