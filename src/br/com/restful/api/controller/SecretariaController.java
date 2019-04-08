package br.com.restful.api.controller;

import java.util.ArrayList;

import br.com.restful.api.dao.SecretariaDAO;
import br.com.restful.api.model.Secretaria;

public class SecretariaController {
	/**
	 * 
	 * Chama o metodo listarTodos da classe ClienteDAO
	 */
	public ArrayList<Secretaria> listarTodos() {
		System.out.println("Controller: listarTodos ");
		return SecretariaDAO.getInstance().listarTodos();

	}

	/**
	 * Chama o metodo getById da classe ClienteDAO
	 */
	public Secretaria buscarPorId(long id) {
		System.out.println("Controller: buscarPorId - " + id);
		SecretariaDAO dao = new SecretariaDAO();
		Secretaria secretaria = dao.getById(id);
		return secretaria;
	}

	/**
	 * Chama o metodo inset da classe ClienteDAO
	 */
	public Secretaria gravarSecretaria(Secretaria secretaria) {
		System.out.println("Controller: gravarSecretaria " + secretaria.getNome());
		return new SecretariaDAO().insert(secretaria);
	}

	/**
	 * Chama o metodo update na classe ClienteDAO
	 */
	public boolean atualizarSecretaria(Secretaria secretaria) {
		System.out.println("Controller: atualizarSecretaria " + secretaria.getNome());
		return SecretariaDAO.getInstance().update(secretaria);
	}

	/**
	 * Chama o metodo delete na classe ClienteDAO
	 */
	public boolean deletarSecretaria(Secretaria secretaria) {
		System.out.println("Controller: deletarSecretaria " + secretaria.getNome());
		return SecretariaDAO.getInstance().delete(secretaria);
	}

}
