package br.com.restful.api.dao;

import java.util.List;

import br.com.restful.api.model.Secretaria;

public interface SecretariaDAOinterface {

	public Secretaria save(Secretaria secretaria);

	public boolean delete(Secretaria secretaria);

	public boolean update(Secretaria secretaria);

	public Secretaria findById(Secretaria secretaria);

	public List<Secretaria> findByName(Secretaria secretaria);

	public List<Secretaria> findAll();
}
