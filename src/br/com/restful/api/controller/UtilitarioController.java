package br.com.restful.api.controller;

import java.util.ArrayList;

import br.com.restful.api.dao.UtilitarioDAO;
import br.com.restful.api.model.Utilitario;

public class UtilitarioController {
	/**
	 * 
	 * Chama o metodo listarTodos da classe UtilitarioDAO
	 */
	public ArrayList<Utilitario> listarTodos() {
		System.out.println("Controller: listarTodos ");
		return UtilitarioDAO.getInstance().listarTodos();

	}

	/**
	 * Chama o metodo getById da classe UtilitarioDAO
	 */
	public Utilitario buscarPorId(long id) {
		System.out.println("Controller: buscarPorId - " + id);
		UtilitarioDAO dao = new UtilitarioDAO();
		Utilitario utilitario = dao.getById(id);
		return utilitario;
	}

	/**
	 * Chama o metodo inset da classe UtilitarioDAO
	 */
	public Utilitario gravarUtilitario(Utilitario utilitario) {
		System.out.println("Controller: gravarUtilitario " + utilitario.getNome());
		return new UtilitarioDAO().insert(utilitario);
	}

	/**
	 * Chama o metodo update na classe UtilitarioDAO
	 */
	public boolean atualizarUtilitario(Utilitario utilitario) {
		System.out.println("Controller: atualizarUtilitario " + utilitario.getNome());
		return UtilitarioDAO.getInstance().update(utilitario);
	}

	/**
	 * Chama o metodo delete na classe UtilitarioDAO
	 */
	public boolean deletarUtilitario(Utilitario utilitario) {
		System.out.println("Controller: deletarUtilitario " + utilitario.getNome());
		return UtilitarioDAO.getInstance().delete(utilitario);
	}

}
