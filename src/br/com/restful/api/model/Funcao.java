package br.com.restful.api.model;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * 
 * Classe responsavel por conter os atributos do Objeto Cliente
 * 
 */
@XmlRootElement
public final class Funcao {

	private Integer id;
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
