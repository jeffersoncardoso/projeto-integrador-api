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

import br.com.restful.api.controller.SecretariaController;
import br.com.restful.api.model.Secretaria;

/**
 * Classe responsavel por conter os metodos REST de acesso ao webservice
 */
@Path("/secretaria")
public class SecretariaResource {
	Gson gson = new Gson();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listarTodos() {
		String r = gson.toJson(new SecretariaController().listarTodos());
		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		Secretaria secretaria = new SecretariaController().buscarPorId(id);
		if (secretaria != null) {
			return Response.status(Response.Status.OK).entity(gson.toJson(secretaria)).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"false\"}").build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarSecretariaJson(String s) {
		Secretaria secretaria = gson.fromJson(s, Secretaria.class);
		Secretaria newSecretaria = new SecretariaController().gravarSecretaria(secretaria);
		if (!newSecretaria.getId().equals(null)) {
			return Response.status(Response.Status.OK).entity(gson.toJson(newSecretaria)).build();
		} else { 
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"false\"}").build();
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response altualizarSecretaria(String s) {
		Secretaria secretaria = gson.fromJson(s, Secretaria.class);
		boolean isSecretariaAtualizado = new SecretariaController().atualizarSecretaria(secretaria);
		if (isSecretariaAtualizado == true) {
			return Response.status(Response.Status.OK).entity(s).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"false\"}").build();
		}

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarSecretaria(String s) {
		Secretaria secretaria = gson.fromJson(s, Secretaria.class);
		boolean isSecretariaDeletado = new SecretariaController().deletarSecretaria(secretaria);
		if (isSecretariaDeletado == true) {
			return Response.status(Response.Status.OK).entity("{\"message\":\"true\"}").build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"false\"}").build();
		}

	}

}
