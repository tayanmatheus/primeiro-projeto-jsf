package br.edu.faculdadedelta.primeiroprojetojsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.primeiroprojetojsf.modelo.Aluno;
import br.edu.faculdadedelta.primeiroprojetojsf.util.Conexao;

public class AlunoDAO {

	// C R U D
	
	public void incluir(Aluno aluno) 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "insert into alunos (nome_aluno, matricula_aluno) "
				+ " VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, aluno.getNome().trim());
		ps.setString(2, aluno.getMatricula().trim());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void alterar(Aluno aluno) 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "UPDATE alunos SET nome_aluno = ?, "
				+ " matricula_aluno = ? "
				+ " WHERE id_aluno = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, aluno.getNome().trim());
		ps.setString(2, aluno.getMatricula().trim());
		ps.setLong(3, aluno.getId());
		
		ps.executeUpdate();

		ps.close();
		conn.close();
	}
	
	public void excluir(Aluno aluno) 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM alunos WHERE id_aluno = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, aluno.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public List<Aluno> listar() 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_aluno, nome_aluno, matricula_aluno FROM alunos";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Aluno> listaRetorno = new ArrayList<>();
		while(rs.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(rs.getLong("id_aluno"));
			aluno.setNome(rs.getString("nome_aluno").trim());
			aluno.setMatricula(rs.getString("matricula_aluno").trim());
			listaRetorno.add(aluno);
		}
		rs.close();
		ps.close();
		conn.close();
		return listaRetorno;
	}
}













