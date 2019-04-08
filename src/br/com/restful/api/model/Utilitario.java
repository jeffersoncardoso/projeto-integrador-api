package br.com.restful.api.model;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * 
 * Classe responsavel por conter os atributos do Objeto Utilitario
 * 
 */
@XmlRootElement
public final class Utilitario {

	private Integer id;
	private String nome;
	private String icone;
	private String descrisao;
	private String status;
	private Integer iditem;


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

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getDescrisao() {
		return descrisao;
	}

	public void setDescrisao(String descrisao) {
		this.descrisao = descrisao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIdItem() {
		return iditem;
	}

	public void setIdItem(Integer iditem) {
		this.iditem = iditem;
	}
}
