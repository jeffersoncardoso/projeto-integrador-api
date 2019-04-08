package br.com.restful.api.model;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * 
 * Classe responsavel por conter os atributos do Objeto Cliente
 * 
 */
@XmlRootElement
public final class Sistema {

	private Integer id;
	private String nome;
	private String icone;
	private String nome_abreviado;
	private String responsavel;
	private String sobre;
	private String producao;
	private String homologacao;
	private String desenvolvimento;
	private String status;
	private String tipo;
	private int iditem;
	private boolean operacao;
	private boolean servico;
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
	public String getNome_abreviado() {
		return nome_abreviado;
	}
	public void setNome_abreviado(String nome_abreviado) {
		this.nome_abreviado = nome_abreviado;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public String getSobre() {
		return sobre;
	}
	public void setSobre(String sobre) {
		this.sobre = sobre;
	}
	public String getProducao() {
		return producao;
	}
	public void setProducao(String producao) {
		this.producao = producao;
	}
	public String getHomologacao() {
		return homologacao;
	}
	public void setHomologacao(String homologacao) {
		this.homologacao = homologacao;
	}
	public String getDesenvolvimento() {
		return desenvolvimento;
	}
	public void setDesenvolvimento(String desenvolvimento) {
		this.desenvolvimento = desenvolvimento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getIditem() {
		return iditem;
	}
	public void setIditem(int iditem) {
		this.iditem = iditem;
	}
	public boolean isOperacao() {
		return operacao;
	}
	public void setOperacao(boolean operacao) {
		this.operacao = operacao;
	}
	public boolean isServico() {
		return servico;
	}
	public void setServico(boolean servico) {
		this.servico = servico;
	}
}
