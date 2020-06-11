package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.faculdadedelta.dao.MotoristaDAOYgorMendonca;
import br.com.faculdadedelta.modelo.CnhYgorMendonca;
import br.com.faculdadedelta.modelo.MotoristaYgorMendonca;
import br.com.faculdadedelta.util.FacesUtil;

@ManagedBean
@SessionScoped
public class MotoristaControllerYgorMendonca {

	MotoristaYgorMendonca motoristaYgorMendonca = new MotoristaYgorMendonca();
	MotoristaDAOYgorMendonca dao = new MotoristaDAOYgorMendonca();
	CnhYgorMendonca cnhSelecionado = new CnhYgorMendonca();
	
	private static final String PAGINA_CADASTRO_MOTORISTA = "cadastroMotoristaYgorMendonca.xhtml";
	private static final String PAGINA_LISTA_MOTORISTA = "listaMotoristaYgorMendonca.xhtml";
	
	public MotoristaYgorMendonca getMotoristaYgorMendonca() {
		return motoristaYgorMendonca;
	}

	public void setMotoristaYgorMendonca(MotoristaYgorMendonca motoristaYgorMendonca) {
		this.motoristaYgorMendonca = motoristaYgorMendonca;
	}

	public CnhYgorMendonca getCnhSelecionado() {
		return cnhSelecionado;
	}

	public void setCnhSelecionado(CnhYgorMendonca cnhSelecionado) {
		this.cnhSelecionado = cnhSelecionado;
	}

	public void limparCampos() {
		motoristaYgorMendonca = new MotoristaYgorMendonca();
		cnhSelecionado = new CnhYgorMendonca();
	}

	public String salvar() {
		try {
			if (motoristaYgorMendonca.getId() == 0) {
				motoristaYgorMendonca.setCnhYgorMendonca(cnhSelecionado);
				dao.incluir(motoristaYgorMendonca);
				FacesUtil.exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				motoristaYgorMendonca.setCnhYgorMendonca(cnhSelecionado);
				dao.alterar(motoristaYgorMendonca);
				FacesUtil.exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}

		return PAGINA_CADASTRO_MOTORISTA;
	}

	public String excluir() {
		try {
			dao.excluir(motoristaYgorMendonca);
			FacesUtil.exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}
		return PAGINA_LISTA_MOTORISTA;
	}

	public String editar() {
		cnhSelecionado = motoristaYgorMendonca.getCnhYgorMendonca();
		return PAGINA_CADASTRO_MOTORISTA;
	}

	public List<MotoristaYgorMendonca> getLista() {
		List<MotoristaYgorMendonca> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}
		return listaRetorno;
	}
}