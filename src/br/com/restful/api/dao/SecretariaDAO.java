package br.com.restful.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import br.com.restful.api.factory.ConnectionFactory;
import br.com.restful.api.model.Secretaria;

/**
 * Classe responsovel por conter os metodos do CRUD.
 */
public class SecretariaDAO extends ConnectionFactory {

	private static SecretariaDAO instance;

	/**
	 * Metodo responsovel por criar uma instancia da classe ClienteDAO.
	 */
	public static SecretariaDAO getInstance() {
		if (instance == null)
			instance = new SecretariaDAO();
		return instance;
	}

	/**
	 * @return ArrayList<Cliente>
	 * Metodo responsavel por buscar e listar todos os clientes gravados no  banco de dados.
	 */
	public ArrayList<Secretaria> listarTodos() {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Secretaria> secretarias = null;

		conexao = criarConexao();
		secretarias = new ArrayList<Secretaria>();
		try {
			pstmt = conexao
					.prepareStatement("SELECT * FROM secretaria ORDER BY nome");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Secretaria secretaria = new Secretaria();

				secretaria.setId(rs.getInt("id"));
				secretaria.setNome(rs.getString("nome"));
				secretarias.add(secretaria);
			}

		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return secretarias;
	}

	/**
	 * Busca um cliente no banco dado um id.
	 * 
	 * @param id
	 * @return cliente
	 */
	public Secretaria getById(long id) {

		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Secretaria secretaria = null;
		conexao = criarConexao();

		try {
			pstmt = conexao
					.prepareStatement("SELECT * FROM secretaria WHERE id = ?");
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				secretaria = new Secretaria();
				secretaria.setId(rs.getInt("id"));
				secretaria.setNome(rs.getString("nome"));
			}
		} catch (Exception e) {
			System.out
					.println("Erro ao buscar secretaria com ID=" + id + "\n" + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}

		return secretaria;

	}

	/**
	 * Metodo responsavel por gravar cliente no banco de dados.
	 * 
	 * @param cliente
	 * @return verdade se cliente gravado e falso se nao gravado
	 */
	public Secretaria insert(Secretaria secretaria) {
		String nome = secretaria.getNome();
		PreparedStatement pstmt = null;
		Connection conexao = criarConexao();
		try {
			pstmt = conexao
					.prepareStatement("insert into secretaria(nome)"
							+ "values(?)", PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nome);
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				secretaria.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return secretaria;
	}

	/**
	 * Metodo responsavel por atualizar cliente na base de dados
	 * 
	 * @param cliente
	 * @return verdade se atualizado e falso se nao.
	 */
	public boolean update(Secretaria secretaria) {
		long id = secretaria.getId();
		System.out.println(id);
		String nome = secretaria.getNome();
		boolean isAtualizado = false;
		PreparedStatement pstmt = null;
		Connection conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("UPDATE secretaria SET nome =? WHERE id = ?");
			pstmt.setString(1, nome);
			pstmt.setLong(2, id);
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
	 * Metodo responsavel por deletar cliente na base de dados.
	 * 
	 * @param id
	 * @return Verdade se cliente deletado e falso se nao.
	 */
	public boolean delete(Secretaria secretaria) {
		boolean isDeletado = false;
		PreparedStatement pstmt = null;
		Connection conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("DELETE FROM secretaria WHERE id = ?");
			pstmt.setInt(1, secretaria.getId());
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
