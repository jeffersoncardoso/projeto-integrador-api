package br.com.restful.api.dao;

import java.util.List;

import br.com.restful.api.model.Utilitario;

public interface UtilitarioDAOinterface {

	public Utilitario save(Utilitario utilitario);

	public boolean delete(Utilitario utilitario);

	public boolean update(Utilitario utilitario);

	public Utilitario findById(Utilitario utilitario);

	public List<Utilitario> findByName(Utilitario utilitario);

	public List<Utilitario> findAll();
}
