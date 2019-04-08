package br.com.restful.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import br.com.restful.api.factory.ConnectionFactory;
import br.com.restful.api.model.Sistema;

/**
 * Classe responsovel por conter os metodos do CRUD.
 */
public class SistemaDAO extends ConnectionFactory {

	private static SistemaDAO instance;

	/**
	 * Metodo responsovel por criar uma instancia da classe SistemaDAO.
	 */
	public static SistemaDAO getInstance() {
		if (instance == null)
			instance = new SistemaDAO();
		return instance;
	}

	/**
	 * @return ArrayList<Sistema>
	 * Metodo responsavel por buscar e listar todos os sistemas gravados no  banco de dados.
	 */
	public ArrayList<Sistema> listarTodos() {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Sistema> sistemas = null;

		conexao = criarConexao();
		sistemas = new ArrayList<Sistema>();
		try {
			pstmt = conexao
					.prepareStatement("SELECT * FROM sistema ORDER BY nome");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Sistema sistema = new Sistema();

				sistema.setId(rs.getInt("id"));
				sistema.setNome(rs.getString("nome"));
				sistema.setIcone(rs.getString("icone"));
				sistema.setNome_abreviado(rs.getString("nome_abreviado"));
				sistema.setResponsavel(rs.getString("responsavel"));
				sistema.setSobre(rs.getString("sobre"));
				sistema.setProducao(rs.getString("producao"));
				sistema.setHomologacao(rs.getString("homologacao"));
				sistema.setDesenvolvimento(rs.getString("desenvolvimento"));
				sistema.setStatus(rs.getString("status"));
				sistema.setTipo(rs.getString("tipo"));
				sistema.setIditem(rs.getInt("iditem"));
				sistema.setOperacao(rs.getBoolean("operacao"));
				sistema.setServico(rs.getBoolean("servico"));
				
				sistemas.add(sistema);
			}		

		} catch (Exception e) {
			System.out.println("Erro ao listar todos os utilitarios: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return sistemas;
	}

	/**
	 * Busca um Sistema no banco dado um id.
	 */
	public Sistema getById(long id) {

		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Sistema sistema = null;
		conexao = criarConexao();

		try {
			pstmt = conexao
					.prepareStatement("SELECT * FROM sistema WHERE id = ?");
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sistema = new Sistema();
				sistema.setId(rs.getInt("id"));
				sistema.setNome(rs.getString("nome"));
				sistema.setIcone(rs.getString("icone"));
				sistema.setNome_abreviado(rs.getString("nome_abreviado"));
				sistema.setResponsavel(rs.getString("responsavel"));
				sistema.setSobre(rs.getString("sobre"));
				sistema.setProducao(rs.getString("producao"));
				sistema.setHomologacao(rs.getString("homologacao"));
				sistema.setDesenvolvimento(rs.getString("desenvolvimento"));
				sistema.setStatus(rs.getString("status"));
				sistema.setTipo(rs.getString("tipo"));
				sistema.setIditem(rs.getInt("iditem"));
				sistema.setOperacao(rs.getBoolean("operacao"));
				sistema.setServico(rs.getBoolean("servico"));
			}
		} catch (Exception e) {
			System.out
					.println("Erro ao buscar Sistema com ID=" + id + "\n" + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}

		return sistema;

	}

	/**
	 * Metodo responsavel por gravar Sistema no banco de dados.
	 */
	
	public Sistema insert(Sistema sistema) {
		
		String nome = sistema.getNome();
		String icone = sistema.getIcone();
		String nome_abreviado = sistema.getNome_abreviado();
		String responsavel = sistema.getResponsavel();
		String sobre = sistema.getSobre();
		String producao = sistema.getProducao();
		String homologacao = sistema.getHomologacao();
		String desenvolvimento = sistema.getDesenvolvimento();
		String status = sistema.getStatus();
		String tipo = sistema.getTipo();
		boolean operacao = sistema.isOperacao();
		boolean servico = sistema.isServico();
		
		PreparedStatement pstmt = null;
		Connection conexao = criarConexao();
		try {
			
			pstmt = conexao
					.prepareStatement("insert into item (id) values (NULL)", PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				sistema.setIditem(rs.getInt(1));
			}
			pstmt = null;
			conexao = criarConexao();
			pstmt = conexao
					.prepareStatement("insert into sistema(nome, icone, nome_abreviado, responsavel, sobre, producao, "
							+ "homologacao, desenvolvimento, status, tipo, iditem, operacao, servico) "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, nome);
			pstmt.setString(2, icone);
			pstmt.setString(3, nome_abreviado);
			pstmt.setString(4, responsavel);
			pstmt.setString(5, sobre);
			pstmt.setString(6, producao);
			pstmt.setString(7, homologacao);
			pstmt.setString(8, desenvolvimento);
			pstmt.setString(9, status);
			pstmt.setString(10, tipo);
			pstmt.setInt(11, sistema.getIditem());
			pstmt.setBoolean(12, operacao);
			pstmt.setBoolean(13, servico);
			pstmt.execute();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				sistema.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sistema;
	}

	/**
	 * Metodo responsavel por atualizar Sistema na base de dados
	 */
	
	public boolean update(Sistema sistema) {
        
		int id = sistema.getId();
		String nome = sistema.getNome();
		String icone = sistema.getIcone();
		String nome_abreviado = sistema.getNome_abreviado();
		String responsavel = sistema.getResponsavel();
		String sobre = sistema.getSobre();
		String producao = sistema.getProducao();
		String homologacao = sistema.getHomologacao();
		String desenvolvimento = sistema.getDesenvolvimento();
		String status = sistema.getStatus();
		String tipo = sistema.getTipo();
		int iditem = sistema.getIditem();
		boolean operacao = sistema.isOperacao();
		boolean servico = sistema.isServico();
		
		boolean isAtualizado = false;
		PreparedStatement pstmt = null;
		Connection conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("UPDATE sistema SET nome =?, icone =?, nome_abreviado =?, responsavel =?, sobre = ?,"
					+ "producao =?, homologacao =?, desenvolvimento =?, status =?, tipo =?, iditem =?, operacao =?, servico =?  WHERE id = ?");
			
			pstmt.setString(1, nome);
			pstmt.setString(2, icone);
			pstmt.setString(3, nome_abreviado);
			pstmt.setString(4, responsavel);
			pstmt.setString(5, sobre);
			pstmt.setString(6, producao);
			pstmt.setString(7, homologacao);
			pstmt.setString(8, desenvolvimento);
			pstmt.setString(9, status);
			pstmt.setString(10, tipo);
			pstmt.setInt(11, iditem);
			pstmt.setBoolean(12, operacao);
			pstmt.setBoolean(13, servico);
			pstmt.setInt(14, id);
			
			int execute = pstmt.executeUpdate();
			if(execute == 1) {
				isAtualizado = true;
			}

		} catch (SQLException e) {
			isAtualizado = false;
			e.printStackTrace();

		} finally {
			fecharConexao(conexao, pstmt, null);
		}
		return isAtualizado;

	}

	/**
	 * Metodo responsavel por deletar sistema na base de dados.
	 */
	public boolean delete(Sistema sistema) {
		boolean isDeletado = false;
		PreparedStatement pstmt = null;
		Connection conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("DELETE FROM sistema WHERE id = ?");
			pstmt.setInt(1, sistema.getId());
			int execute = pstmt.executeUpdate();
			if(execute == 1) {
				isDeletado = true;
			}
		} catch (SQLException e) {
			isDeletado = false;
			e.printStackTrace();

		} finally {
			fecharConexao(conexao, pstmt, null);
		}
		return isDeletado;
	}

}
