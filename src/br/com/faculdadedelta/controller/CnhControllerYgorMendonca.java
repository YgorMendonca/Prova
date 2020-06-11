package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.faculdadedelta.dao.CnhDAOYgorMendonca;
import br.com.faculdadedelta.modelo.CnhYgorMendonca;
import br.com.faculdadedelta.util.FacesUtil;

@ManagedBean
@SessionScoped
public class CnhControllerYgorMendonca {

	CnhYgorMendonca cnhYgorMendonca = new CnhYgorMendonca();
	CnhDAOYgorMendonca dao = new CnhDAOYgorMendonca();
	
	private static final String PAGINA_CADASTRO_CNH = "cadastroCnhYgorMendonca.xhtml";
	private static final String PAGINA_LISTA_CNH = "listaCnhYgorMendonca.xhtml";
	
	
	public CnhYgorMendonca getCnhYgorMendonca() {
		return cnhYgorMendonca;
	}

	public void setCnhYgorMendonca(CnhYgorMendonca cnhYgorMendonca) {
		this.cnhYgorMendonca = cnhYgorMendonca;
	}

	public void limparCampos() {
		cnhYgorMendonca = new CnhYgorMendonca();
	}
	
	public String salvar() {
		try {
			if (cnhYgorMendonca.getId() == 0) {
				dao.incluir(cnhYgorMendonca);
				FacesUtil.exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(cnhYgorMendonca);
				FacesUtil.exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return PAGINA_CADASTRO_CNH;
	}
	
	public String excluir() {
		try {
			dao.excluir(cnhYgorMendonca);
			FacesUtil.exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação: " + e.getMessage());			
		}
		return PAGINA_LISTA_CNH;
	}
	
	public String editar() {
		return PAGINA_CADASTRO_CNH;
	}
	
	public List<CnhYgorMendonca> getLista() {
		List<CnhYgorMendonca> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação: " + e.getMessage());			
		}
		return listaRetorno;
	}
}