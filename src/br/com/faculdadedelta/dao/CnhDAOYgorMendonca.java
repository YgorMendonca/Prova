package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.CnhYgorMendonca;
import br.com.faculdadedelta.util.ConexaoYgorMendonca;

public class CnhDAOYgorMendonca {

	public void incluir(CnhYgorMendonca cnh) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "INSERT INTO categorias_cnh (descricao_cat) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cnh.getDescCat().trim());
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
	}
	
	public void alterar(CnhYgorMendonca cnh) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "UPDATE categorias_cnh SET descricao_cat = ?  WHERE id_cat = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, cnh.getDescCat().trim());
		ps.setLong(2, cnh.getId());
		
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
	}
	
	public void excluir(CnhYgorMendonca cnh) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "DELETE FROM categorias_cnh WHERE id_cat = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, cnh.getId());
		
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
	}
	
	public CnhYgorMendonca pesquisarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "SELECT id_cat, descricao_cat FROM categorias_cnh WHERE id_cat = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		CnhYgorMendonca retorno = new CnhYgorMendonca();
		if (rs.next()) {
			retorno = popularCnh(rs);
		}
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
		return retorno;
	}
	
	
	public List<CnhYgorMendonca> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "SELECT id_cat, descricao_cat FROM categorias_cnh";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<CnhYgorMendonca> listaRetorno = new ArrayList<>();
		
		while(rs.next()) {
			listaRetorno.add(popularCnh(rs));
		}
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
		return listaRetorno;
	}
	
	private CnhYgorMendonca popularCnh(ResultSet rs) throws SQLException {
		
		CnhYgorMendonca cnh = new CnhYgorMendonca();
		cnh.setId(rs.getLong("id_cat"));
		cnh.setDescCat(rs.getString("descricao_cat").trim());
		
		return cnh;
	}
}