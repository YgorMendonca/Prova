package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.CnhYgorMendonca;
import br.com.faculdadedelta.modelo.MotoristaYgorMendonca;
import br.com.faculdadedelta.util.ConexaoYgorMendonca;

public class MotoristaDAOYgorMendonca {

	public void incluir(MotoristaYgorMendonca motorista) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "INSERT INTO motoristas_viagens (id_cat, nome_motorista, destino, distancia, valor_km, data_corrida) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, motorista.getCnhYgorMendonca().getId());
		ps.setString(2, motorista.getNomeMotorista().trim());
		ps.setString(3, motorista.getDestino().trim());
		ps.setDouble(4, motorista.getDistancia());
		ps.setDouble(5, motorista.getValorKm());
		ps.setDate(6, new java.sql.Date(motorista.getDataCorrida().getTime()));
				
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
	}
	
	public void alterar(MotoristaYgorMendonca motorista) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "UPDATE motoristas_viagens SET id_cat = ?, nome_motorista = ?, destino = ?, distancia = ?, valor_km = ?, data_corrida = ? WHERE id_mot = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, motorista.getCnhYgorMendonca().getId());
		ps.setString(2, motorista.getNomeMotorista().trim());
		ps.setString(3, motorista.getDestino().trim());
		ps.setDouble(4, motorista.getDistancia());
		ps.setDouble(5, motorista.getValorKm());
		ps.setDate(6, new java.sql.Date(motorista.getDataCorrida().getTime()));
		ps.setLong(7, motorista.getId());
		
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
	}
	
	public void excluir(MotoristaYgorMendonca motorista) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "DELETE FROM motoristas_viagens WHERE id_mot = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, motorista.getId());
		
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
	}
		
	public List<MotoristaYgorMendonca> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "SELECT "
				+ " m.id_mot AS idMot, "
				+ " m.nome_motorista AS nomeMotorista, "
				+ " m.destino AS destinoM, "
				+ " m.distancia AS distanciaM, "
				+ " m.valor_km AS valorKm, "
				+ " m.data_corrida AS dataCorrida, "
				+ " c.id_cat AS idCat, "
				+ " c.descricao_cat AS descricaoCat "
				+ " FROM motoristas_viagens m INNER JOIN categorias_cnh c ON m.id_cat = c.id_cat";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MotoristaYgorMendonca> listaRetorno = new ArrayList<>();
		while (rs.next()) {
			
			MotoristaYgorMendonca motorista = new MotoristaYgorMendonca();
			
			motorista.setId(rs.getLong("idMot"));
			motorista.setNomeMotorista(rs.getString("nomeMotorista").trim());
			motorista.setDestino(rs.getString("destinoM").trim());
			motorista.setDistancia(rs.getDouble("distanciaM"));
			motorista.setValorKm(rs.getDouble("valorKm"));
			motorista.setDataCorrida(rs.getDate("dataCorrida"));
									
			CnhYgorMendonca cnh = new CnhYgorMendonca();
			
			cnh.setId(rs.getLong("idCat"));
			cnh.setDescCat(rs.getString("descricaoCat").trim());
					    
		    motorista.setCnhYgorMendonca(cnh);
		  		    
		    listaRetorno.add(motorista);
		}
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
		return listaRetorno;
	}
	
}