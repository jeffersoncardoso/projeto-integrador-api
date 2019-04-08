package br.com.restful.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import br.com.restful.api.factory.ConnectionFactory;
import br.com.restful.api.model.Utilitario;

/**
 * Classe responsovel por conter os metodos do CRUD.
 */
public class UtilitarioDAO extends ConnectionFactory {

	private static UtilitarioDAO instance;

	/**
	 * Metodo responsovel por criar uma instancia da classe UtilitarioDAO.
	 */
	public static UtilitarioDAO getInstance() {
		if (instance == null)
			instance = new UtilitarioDAO();
		return instance;
	}

	/**
	 * @return ArrayList<Utilitario>
	 * Metodo responsavel por buscar e listar todos os utilitarios gravados no  banco de dados.
	 */
	public ArrayList<Utilitario> listarTodos() {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Utilitario> utilitarios = null;

		conexao = criarConexao();
		utilitarios = new ArrayList<Utilitario>();
		try {
			pstmt = conexao
					.prepareStatement("SELECT * FROM utilitario ORDER BY nome");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Utilitario utilitario = new Utilitario();

				utilitario.setId(rs.getInt("id"));
				utilitario.setNome(rs.getString("nome"));
				utilitario.setIcone(rs.getString("icone"));
				utilitario.setDescrisao(rs.getString("descrisao"));
				utilitario.setStatus(rs.getString("status"));
				utilitario.setIdItem(rs.getInt("iditem"));
				utilitarios.add(utilitario);
			}

		} catch (Exception e) {
			System.out.println("Erro ao listar todos os utilitarios: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return utilitarios;
	}

	/**
	 * Busca um utilitario no banco dado um id.
	 */
	public Utilitario getById(long id) {

		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilitario utilitario = null;
		conexao = criarConexao();

		try {
			pstmt = conexao
					.prepareStatement("SELECT * FROM utilitario WHERE id = ?");
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				utilitario = new Utilitario();
				utilitario.setId(rs.getInt("id"));
				utilitario.setNome(rs.getString("nome"));
				utilitario.setIcone(rs.getString("icone"));
				utilitario.setDescrisao(rs.getString("descrisao"));
				utilitario.setStatus(rs.getString("status"));
				utilitario.setIdItem(rs.getInt("iditem"));
			}
		} catch (Exception e) {
			System.out
					.println("Erro ao buscar utilitario com ID=" + id + "\n" + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}

		return utilitario;

	}

	/**
	 * Metodo responsavel por gravar utilitario no banco de dados.
	 */
	
	public Utilitario insert(Utilitario utilitario) {
		String nome = utilitario.getNome();
		String icone = utilitario.getIcone();
		String descrisao = utilitario.getDescrisao();
		String status = utilitario.getStatus();
		int iditem = utilitario.getIdItem();
		PreparedStatement pstmt = null;
		Connection conexao = criarConexao();
		try {
			pstmt = conexao
					.prepareStatement("insert into utilitario(nome, icone, descrisao, status, iditem)"
							+ "values(?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nome);
			pstmt.setString(2, icone);
			pstmt.setString(3, descrisao);
			pstmt.setString(4, status);
			pstmt.setInt(5, iditem);
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				utilitario.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilitario;
	}

	/**
	 * Metodo responsavel por atualizar utilitario na base de dados
	 */
	
	public boolean update(Utilitario utilitario) {
		int id = utilitario.getId();
		String nome = utilitario.getNome();
		String icone = utilitario.getIcone();
		String descrisao = utilitario.getDescrisao();
		String status = utilitario.getStatus();
		int iditem = utilitario.getIdItem();
		boolean isAtualizado = false;
		PreparedStatement pstmt = null;
		Connection conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("UPDATE utilitario SET nome =?, icone =?, descrisao =?,"
					+ " status =?, iditem =? WHERE id = ?");
			pstmt.setString(1, nome);
			pstmt.setString(2, icone);
			pstmt.setString(3, descrisao);
			pstmt.setString(4, status);
			pstmt.setInt(5, iditem);
			pstmt.setInt(6, id);
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
	 * Metodo responsavel por deletar utilitario na base de dados.
	 */
	public boolean delete(Utilitario utilitario) {
		boolean isDeletado = false;
		PreparedStatement pstmt = null;
		Connection conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("DELETE FROM utilitario WHERE id = ?");
			pstmt.setInt(1, utilitario.getId());
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
