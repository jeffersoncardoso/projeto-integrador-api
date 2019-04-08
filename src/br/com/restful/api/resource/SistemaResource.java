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

import br.com.restful.api.controller.SistemaController;
import br.com.restful.api.model.Sistema;

/**
 * Classe responsavel por conter os metodos REST de acesso ao webservice
 */
@Path("/sistema")
public class SistemaResource {
	Gson gson = new Gson();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listarTodos() {
		String r = gson.toJson(new SistemaController().listarTodos());
		return r;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		Sistema sistema = new SistemaController().buscarPorId(id);
		if (sistema != null) {
			return Response.status(Response.Status.OK).entity(gson.toJson(sistema)).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"false\"}").build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarSistemaJson(String s) {
		Sistema sistema = gson.fromJson(s, Sistema.class);
		Sistema newSistema = new SistemaController().gravarSecretaria(sistema);
		if (!newSistema.getId().equals(null)) {
			return Response.status(Response.Status.OK).entity(gson.toJson(newSistema)).build();
		} else { 
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"false\"}").build();
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response altualizarSistema(String s) {
		Sistema sistema = gson.fromJson(s, Sistema.class);
		boolean isSistemaAtualizado = new SistemaController().atualizarSistema(sistema);
		if (isSistemaAtualizado == true) {
			return Response.status(Response.Status.OK).entity(s).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"false\"}").build();
		}

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarSecretaria(String s) {
		Sistema sistema = gson.fromJson(s, Sistema.class);
		boolean isSistemaDeletado = new SistemaController().deletarSistema(sistema);
		if (isSistemaDeletado == true) {
			return Response.status(Response.Status.OK).entity("{\"message\":\"true\"}").build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"false\"}").build();
		}

	}

}
