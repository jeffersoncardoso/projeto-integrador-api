package br.com.restful.api.controller;

import java.util.ArrayList;

import br.com.restful.api.dao.SistemaDAO;
import br.com.restful.api.model.Sistema;

public class SistemaController {
	/**
	 * 
	 * Chama o metodo listarTodos da classe SistemaDAO
	 */
	public ArrayList<Sistema> listarTodos() {
		System.out.println("Controller: listarTodos ");
		return SistemaDAO.getInstance().listarTodos();

	}

	/**
	 * Chama o metodo getById da classe SistemaDAO
	 */
	public Sistema buscarPorId(long id) {
		System.out.println("Controller: buscarPorId - " + id);
		SistemaDAO dao = new SistemaDAO();
		Sistema sistema = dao.getById(id);
		return sistema;
	}

	/**
	 * Chama o metodo inset da classe SistemaDAO
	 */
	public Sistema gravarSecretaria(Sistema sistema) {
		System.out.println("Controller: gravarSistema " + sistema.getNome());
		return new SistemaDAO().insert(sistema);
	}

	/**
	 * Chama o metodo update na classe SistemaDAO
	 */
	public boolean atualizarSistema(Sistema sistema) {
		System.out.println("Controller: atualizarSistema " + sistema.getNome());
		return SistemaDAO.getInstance().update(sistema);
	}

	/**
	 * Chama o metodo delete na classe SistemaDAO
	 */
	public boolean deletarSistema(Sistema sistema) {
		System.out.println("Controller: deletarSecretaria " + sistema.getNome());
		return SistemaDAO.getInstance().delete(sistema);
	}

}
