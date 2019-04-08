package br.com.restful.api.dao;

import java.util.List;

import br.com.restful.api.model.Sistema;;

public interface SistemaDAOinterface {

	public Sistema save(Sistema sistema);

	public boolean delete(Sistema sistema);

	public boolean update(Sistema sistema);

	public Sistema findById(Sistema sistema);

	public List<Sistema> findByName(Sistema sistema);

	public List<Sistema> findAll();
}
